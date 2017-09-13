# Scala challenge (GL)

Each task comes with an object and a suite of tests. All tests can be run with `sbt test`.
To test with the REPL, simply run `scala` in the project's directory and `:load` the appropriate file. Then call the method (eg. `Task2.findExpression(Seq(1, 2, 4), 5)`).

## Task 1

**Exercise**: Longest common (discontiguous) subsequence of two Strings.

The proposed implementation takes full advantage of Scala's functional programming techniques, though the algorithm could be optimised:
* the method is not tail-recursive, which could lead to memory problems if it runs with very long lists
* the time complexity is O(n^2), which is definitely not optimum.

We could improve the algorithm by caching known results of `lcs`. (edit: this optimisation has been implemented (e187b0f))

## Task 2

**Exercise**: Given integers (eg. [2, 3, 5, 6]) and a target number (eg. 42), the program should return an expression (eg. `(2 + 5) * 6`).

My first implementation was only able to resolve affine expressions (`(x + y) * z`, y and z being muted if respectively equal to 0 and 1) (88a88c0).
I then improved it by adding the possible permutations of operations to build one binary trees per permutation of numbers, removing the constraint of having strictly one addition and one multiplication (37e1176).

Though, both solutions are based on bruteforce, computing all the permutations for numbers and operations (until a result is found). Ideas of improvements:
* cache the result of known operations
* use basic algebra to find the end of an expression quickly (eg. for `2 + y = 5`, check if `3` is in the list)