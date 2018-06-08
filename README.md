# PrimeSieve

PrimeSieve is a program which generates the first 1000 primes. It does this by implementing the sieve of Eratosthenes algorithm. This algorithm works by discarding multiples of found primes in the list of unevaluated numbers. More [here](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes).

Think of it as a pipeline. In the beginning there is only a single sieve connected to an input, and output buffer. The writer thread writes numbers into this input buffer. The sieve subsequently evaluates these numbers with its prime (2). It checks whether the number that is under inspection, is a multiple, and only writes the number into its output buffer if this check evaluates to false. This way, after a number has passed through sieve_`x`, you know for certain it can't be a multiple of `x`. The first number it writes to its output buffer, is used to create the next sieve, as this is the next prime. This process is repeated until all first 1000 prime numbers have been found.

The main purpose of this program is, of course, to find the first 1000 prime numbers. However, I see it as an extra challenge to write the program as efficient as possible. For this reason, I keep a list of times it took to complete this, for every version of the program. 

Also note that if your aim is to find prime numbers as long as you are running this program, you can simply remove the `limit` parameter from the `Sieve` class (and its `count == limit` check) to allow it to run as long as you want it to.

