#!/usr/bin/env python3

import matplotlib.pyplot as plt

import statistics as sts

import numpy as np


def plotFP(filename):

  f = open(filename, "r")
  lines = f.readlines()
  for l in lines:
    array = l.split()
    n = int(array[0])
    del array[0]
    array = list(np.float_(array))
    std = sts.stdev(array)
    mean = sts.mean(array)
    plt.errorbar(n, mean,
       yerr=std,
       # marker='o',
       color='k',
       ecolor='k',
       # markerfacecolor='g',
       capsize=1,
       linestyle='None',
       fmt=' ')

    # plt.errorbar(n, mean, yerr=std)

  fig = plt.gcf()
  axes = fig.gca()
  axes.set_ylim([0, 1])

  plt.plot(10, 1)
  plt.title('Evolucion de la fraccion de particulas en el lado izquierdo N = ' + str(2000))

  # plt.title('Evolucion de la fraccion de particulas en el lado derecho N = ' + str(2000))
  plt.xlabel('Paso de la iteracion (N)')
  # plt.ylabel('Fraccion de particulas a la derecha')
  plt.ylabel('Fraccion de particulas a la izquierda')
  plt.show()
  return


plotFP("fpa2000.txt")