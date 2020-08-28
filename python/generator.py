#!/usr/bin/env python3

import random

velocity = [1,2,4,8,16,32]

def generate_file(name, num_particles, length):
  dynamic_file = open(name, 'w')
  dynamic_file.write(str(num_particles) + '\n' + '\n')
  for i in range(0, num_particles):
    dynamic_file.write(str(random.uniform(0, length)) + '  ' 
      + str(random.uniform(0, length)) + '  ' + str(velocity[random.randint(0,5)]) + '\n')
  return

num_particles = input("Insert number of particles:\n")
try:
  part_int = int(num_particles)
  generate_file('input.txt', part_int, 200)
except ValueError:
    print("Invalid input")