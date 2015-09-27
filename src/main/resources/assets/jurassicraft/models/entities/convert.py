import os
import re
import contextlib

stages = ["adolescent",
		  #"adult",
		  "infant",
		  "juvenile"]

done = ["velociraptor"]

def sub_dirs(path):
	# See http://stackoverflow.com/questions/973473/
	return next(os.walk(path))[1]

def sub_files(path):
	return next(os.walk(path))[2]

def modify_json(path_in, path_out, stage):
	try:
		with contextlib.ExitStack() as stack:
			original = stack.enter_context(open(path_in, "r"))
			output = stack.enter_context(open(path_out, "w"))
			pattern = re.compile(r'"pose":\s*"(\w+?)_(\w+)(\.tbl)?"')
			subst = r'"pose": "\1_{stage}_\2"'.format(stage=stage)
			for line in original.readlines():
				lineout = re.sub(pattern, subst, line)
				output.write(lineout)
			print(path_in, "!!", path_out)
		os.remove(path_in)
	except:
		print("Failed: ", path_in)
		raise

if __name__ == "__main__":
	for dino in sub_dirs("."):
		dino_dir = os.path.join(".", dino)
		if dino in done:
			continue
		for stage in sub_dirs(dino_dir):
			if stage not in stages:
				continue
			stage_dir = os.path.join(dino_dir, stage)
			for anim in sub_files(stage_dir):
				anim_file = os.path.join(stage_dir, anim)
				if anim == dino + ".json":
					new_name = "{dino}_{stage}.json".format(dino=dino, stage=stage)
					new_file = os.path.join(stage_dir, new_name)
					modify_json(anim_file, new_file, stage)
				else:
					pattern = re.compile("{dino}_([^_]+).tbl".format(dino=dino))
					match = re.match(pattern, anim)
					if match is None:
						print("//", anim_file)
						continue
					anim_name = match.group(1)
					new_name = "{dino}_{stage}_{name}.tbl".format(dino=dino, stage=stage, name=anim_name)
					new_file = os.path.join(stage_dir, new_name)
					os.rename(anim_file, new_file)
					print(anim_file, "->", new_file)
