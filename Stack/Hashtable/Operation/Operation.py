from enum import Enum
from math import sqrt

__author__ = "Ionesio Junior"

class Operation(Enum):
	''' ENUM used to select some method to generate the key '''
	MULTIPLICATION = 1
	DIVISION = 2


class Division(object):
	''' Division Method 
        	
	    Attributes:
		tableSize(int) : size of internal table in hashtable class
        '''
	__tableSize = None
	
	def __init__(self,tableSize):
		''' Division Operation constructor,initialize attributes'''
		self.__tableSize = tableSize
	
	
	def hashFunc(self,element):
		''' Method used to generate key for some element 
                
                    Args:
			element(optional) : element to generate key
		
		   Returns:
			hashKey(int) : key of element
                '''
		hashKey = -1
		key = hash(element)
		hashKey = int(key % self.__tableSize)
		return hashKey



class Multiplication(object):
        ''' Multiplication Method 
                
            Attributes:
                tableSize(int) : size of internal table in hashtable class
		CONSTANT(float) : constant to help defines key in hashFunc (CONSTANT = sqrt(5) - 1 / 2)
        '''
	__tableSize = None
	__CONSTANT = float(float(sqrt(5) - 1)  / 2)
	def __init__(self,tableSize):
		''' 
			Multiplication Method constructor,initialize attributes
		'''
		self.__tableSize = tableSize

	def hashFunc(self,element):
                ''' Method used to generate key for some element 
                
                    Args:
                        element(optional) : element to generate key
                
                   Returns:
                        hashKey(int) : key of element
                '''
		hasKey = -1
		key = hash(element)
		fractionalPart = key * self.__CONSTANT - int(key *self.__CONSTANT)
		hashKey = int(self.__tableSize * fractionalPart)
		return hashKey
