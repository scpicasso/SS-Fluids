#!/usr/bin/env python3

import matplotlib.pyplot as plt

import statistics as sts

import numpy as np


def plotEVO(filename):

  f = open(filename, "r")
  lines = f.readlines()
  for l in lines:
    array = l.split()
    n = int(array[0])
    del array[0]
    array = list(np.int_(array))
    std = sts.stdev(array)
    mean = sts.mean(array)
    plt.errorbar(n, mean,
       yerr=std,
       marker='o',
       color='k',
       ecolor='k',
       # markerfacecolor='g',
       capsize=1,
       linestyle='None',
       fmt=' ')

    # plt.errorbar(n, mean, yerr=std)

  fig = plt.gcf()
  axes = fig.gca()
  axes.set_ylim([500, 2000])
  axes.set_xlim([0, 7000])

  plt.plot(10, 1)
  plt.title('Tiempo hasta conseguir el equilibrio')

  plt.xlabel('Particulas')
  plt.ylabel('Cantidad de iteraciones')
  plt.show()
  return


plotEVO("equilibrio.txt")