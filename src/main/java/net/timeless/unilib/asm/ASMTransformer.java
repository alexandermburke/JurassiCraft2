package net.timeless.unilib.asm;

import com.google.common.collect.Lists;
import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;
import java.util.List;


@IFMLLoadingPlugin.Name(value = "MyCoreMod") // The readable mod name
@IFMLLoadingPlugin.MCVersion(value = "1.8")
// The MC version it is designed for (Remember? Upwards/Downwards compatibility lost!)
@IFMLLoadingPlugin.TransformerExclusions(value = "net.timeless.asm")
// Your whole core mod package - Whatever you don't want the transformers to run over to prevent circularity Exceptions
@IFMLLoadingPlugin.SortingIndex(value = Integer.MAX_VALUE)
// How early your core mod is called - Use > 1000 to work with srg names
public class ASMTransformer implements IClassTransformer
{

    private final File folder;
    private final List<String> filesToDebug;

    public ASMTransformer()
    {
        folder = new File(".", "asm/debug");
        if (!folder.exists())
            folder.mkdirs();
        filesToDebug = Lists.newArrayList();
        String options = System.getProperty("unilibAsmDebug");
        if (options != null)
        {
            String[] classes = options.split(";");
            for (String clazz : classes)
            {
                filesToDebug.add(clazz.replace(".", "/"));
            }
        }
    }

    @Override
    public byte[] transform(String className, String s1, byte[] bytes)
    {
        // Unilib does not operate on the bytecode but only outputs the bytecode as text and as a class file inside the
        // gameFolder/debug/ folder
        String name = className.replace(".", "/");
        if (name.equals("net/minecraft/item/ItemStack"))
        {
            // TODO: Modify ItemStack::getMetadata()
        }
        if (filesToDebug.contains(name))
        {
            try
            {
                File textFile = createFile(folder, className, "txt");
                ClassReader reader = new ClassReader(bytes);
                TraceClassVisitor visitor = new TraceClassVisitor(new PrintWriter(new FileWriter(textFile)));
                reader.accept(visitor, 0);
                File classFile = createFile(folder, className, "class");
                FileOutputStream out = new FileOutputStream(classFile);
                out.write(bytes);
                out.flush();
                out.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    private File createFile(File folder, String className, String extension) throws IOException
    {
        File result = new File(folder, className.replace(".", "/") + "." + extension);
        if (!result.getParentFile().exists())
            result.getParentFile().mkdirs();
        result.createNewFile();
        return result;
    }
}
