#!/usr/bin/env python3

import matplotlib.pyplot as plt


def M_compare(filename):
  f = open(filename, "r")
  lines = f.readlines()
  # params = lines[0].split()
  # n = int(params[0])
  # del lines[0]
  for l in lines:
    array = l.split()
    # plt.plot(float(array[0]), float(array[1]), c = 'red')

    plt.errorbar(float(array[0]), float(array[1]),
       yerr=0,
       marker='o',
       color='k',
       ecolor='k',
       markerfacecolor='g',
       label="series 2",
       capsize=5,
       linestyle='None',
       fmt=' ')

  plt.plot(10, 1)
  plt.title('Time comparison with Delta particles, N = ' + str(5000))
  plt.xlabel('Iteration Step')
  plt.ylabel('Delta particles in chambers')
  plt.show()
  return


M_compare("fpTime.txt")