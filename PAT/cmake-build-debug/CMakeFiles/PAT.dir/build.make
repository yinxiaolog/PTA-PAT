# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.14

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/mac/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/PAT.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/PAT.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/PAT.dir/flags.make

CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o: CMakeFiles/PAT.dir/flags.make
CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o: ../src/pat_basic_level_1001.cc
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o"
	/Library/Developer/CommandLineTools/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o -c /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/src/pat_basic_level_1001.cc

CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.i"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/src/pat_basic_level_1001.cc > CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.i

CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.s"
	/Library/Developer/CommandLineTools/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/src/pat_basic_level_1001.cc -o CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.s

# Object files for target PAT
PAT_OBJECTS = \
"CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o"

# External object files for target PAT
PAT_EXTERNAL_OBJECTS =

PAT: CMakeFiles/PAT.dir/src/pat_basic_level_1001.cc.o
PAT: CMakeFiles/PAT.dir/build.make
PAT: CMakeFiles/PAT.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable PAT"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/PAT.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/PAT.dir/build: PAT

.PHONY : CMakeFiles/PAT.dir/build

CMakeFiles/PAT.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/PAT.dir/cmake_clean.cmake
.PHONY : CMakeFiles/PAT.dir/clean

CMakeFiles/PAT.dir/depend:
	cd /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug /Users/yinxiaolong/CLionProjects/gitRepo/PTA-PAT/PAT/cmake-build-debug/CMakeFiles/PAT.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/PAT.dir/depend

