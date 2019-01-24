# ntbea

## N-Tuple Bandit Evolutionary Algorithm (NTBEA)

The NTBEA combines multi-armed bandits (actually combinatorial multi-armed bandits) with an evolutionary 
algorithm to provide a sample-efficient optimisation algorithm.  Currently the algorithm works only in discrete search spaces.
Continuous parameters can be handled by discretising them.  We're working on an extended version that
will also handle continuous parameters.  Meanwhile you'll need to use some insight in to
what makes a reasonable discretisation for each parameter.

## Aims

NTBEA was developed to handle noisy optimisation problems in a sample-efficient way. The algorithm
analyses the contribution of each individual parameter value, together with combinations of parameter
values.  Each combination is referred to as an N-Tuple.  Typically we model each parameter individually
(the 1-Tuples), then each pair (2-Tuples), sometimes the 3-Tuples and nearly always the single N-Tuple
(where the search space is N-dimensional).

A modified form of bandit equation (UCB with a type of progressive widening) is used
to balance exploiting what already appear to be good parameter settings with exploring
those that have not yet been sampled much.

In addition to providing efficient optimisation NTBEA also provides detailed statistics
on each parameter choice.  See the example here: [Sample Output](docs/SampleOutput.md).

To understand the algorithm see our IEEE CEC 2018 paper (nominated for the best paper award)
https://arxiv.org/abs/1802.05991

For a comparison with other approaches see our [AAAI Games and Simulations for AI](https://www.gamesim.ai) workshop paper:
https://arxiv.org/abs/1901.00723 (or see quick summary in our [poster](docs/AAAIWorkshopPoster.pdf))




## Versions

This repo contains the Java version of NTBEA.

The Python version is here:  https://github.com/bam4d/NTBEA

## See also

Sequential Model Based Algorithm Configuration (SMAC) - a well established general algorithm optimiser - works with continuous
and discrete parameter values:
https://github.com/automl/SMAC3



Nevergrad - continuous optimisation toolbox:
https://github.com/facebookresearch/nevergrad

CMA-ES:
http://cma.gforge.inria.fr/



## Citing

### The first paper on NTBEA and its use for Game Tuning:

```bibtex
@inproceedings{NTBEA-Game-Tuning,
  title={The N-Tuple Bandit Evolutionary Algorithm for Automatic Game Improvement},
  author={Kunanusont, Kamolwan and Gaina, Raluca D. and Liu, Jialin and Perez-Liebana, Diego and Lucas, Simon M.},
  booktitle={2017 IEEE Congress on Evolutionary Computation (CEC)},
  note{\url{https://arxiv.org/pdf/1705.01080.pdf}},
  year={2017}
}
```
### NTBEA Applied to Game Agent Tuning:

```bibtex
@article{NTBEA-AgentTuning,
  title={The N-Tuple Bandit Evolutionary Algorithm for Game Agent Optimisation},
  author={Simon M. Lucas and Jialin Liu and Diego Perez-Liebana},
  journal={Proceedings of IEEE Congress on Evolutionary Computation},
  note={\url{https://arxiv.org/abs/1802.05991}},
  year={2018}
}
```
### NTBEA Applied to Game Agent Tuning and Compared with alternatives such as SMAC and CMA-ES:

```bibtex
@misc{NTBEA-efficient-opt,
    author = {Simon M. Lucas and Jialin Liu and Ivan Bravi and Raluca D. Gaina and
                        John Woodward and Vanessa Volz and Diego Perez-Liebana},
    title = {{Efficient Evolutionary Methods for Game Agent Optimisation: Model-Based is Best}},
    year = {2019},
    publisher = {AAAI},
    journal = {AAAI Workshop on Games and Simulations for Artificial Intelligence},
    howpublished = {\url{https://www.gamesim.ai/}},
}
```
### This repository:

```bibtex
@misc{ntbea-Java,
    author = {Simon M. Lucas},
    title = {{The N-Tuple Bandit Evolutionary Algorithm: Java Version}},
    year = {2018},
    publisher = {GitHub},
    journal = {GitHub repository},
    howpublished = {\url{https://github.com/SimonLucas/ntbea}},
}
```

## License

`NTBEA` is released under the MIT license. See [LICENSE](docs/LICENSE) for additional details.