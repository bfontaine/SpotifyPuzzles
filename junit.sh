#! /bin/bash
# tool to test a .java file with JUnit

__test_sp_puzzles() {
    local PREFIX="$(brew --prefix junit4)/share"

    local puzzle=$1
    local jtest=${1}Test
    local classpath="$puzzle:$PREFIX/junit4.jar:$PREFIX/hamcrest-core-1.3.jar"

    javac -cp "$classpath" $puzzle/${jtest}.java && \
    java -cp "$classpath" org.junit.runner.JUnitCore $jtest
}

__test_sp_puzzles $*
