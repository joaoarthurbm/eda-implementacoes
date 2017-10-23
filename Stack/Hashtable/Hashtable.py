#coding : utf-8
import sys,os
sys.path.append('/Method/')
sys.path.append('/Operation/')
from Methods import *
from Operation import *

__author__ = "Ionesio Junior"


class Hashtable(object):
	''' Abstract hashtable class used in openadress/closedadress implementation 
		
	    Attributes:
		table[] : list to store elements
		elements(int) : how many elements have in internal table		
		maxSize(int) : total capacity of table
		COLLISIONS(int) : number of index collisions in insertions (openadress implementation only)
		function(Linear/Quadratic/Multiplication/Division) : type of function used to generate hash, in openadress(linear/		  quadratic), in closedadress(multiplication/division)
	'''
	table = None
	elements = None
	__maxSize = None
	COLLISIONS = None
	function = None

	def __init__(self,size,operation,method = None):
		''' Hashtable constructor, initialize hashtable attributes
		
		    Args:
			size(int) : size of internal table
			operation(Enum Operation) : defines operation used to generate hashcode(DIVISION/MULTIPLICATION)
			method(Enum Method/None): defines method used to generate hashcode(used only in openadress implementation)
		'''
		self.table = [None] * size
		self.elements = 0
		self.__tableSize = size
	 	self.COLLISIONS = 0
		if(method != None):
			if(method == Method.LINEAR_PROBING):
				self.function = LinearProbing(size,operation)
			elif(method == Method.QUADRATIC_PROBING):
				self.function = QuadraticProbing(size,operation,c1,c2)
		else:
			if(operation == Operation.MULTIPLICATION):
				self.function = Multiplication(self.__tableSize)
			else:
				self.function = Division(self.__tableSize)
		
	def isEmpty(self):
		''' Return true if internal table is empty or false,otherwise '''
		return self.elements ==  0
		
	def isFull(self):
		''' Return true if internal table is full or false,otherwise '''
		return self.elements == self.__tableSize
	
	def capacity(self):
		''' Return total capacity of internal table '''
		return self.__tableSize
	
	
	def size(self):
		''' Return how many elements have in internal table '''
		return self.elements
	
	def getCOLLISIONS(self):
		''' Return how many collisions happened in insertions(only in openadress implementation) '''
		return self.COLLISIONS	
	
	def insert(self,element):
		raise NotImplementedError("Code not implemented")
		
	def remove(self,element):
		raise NotImplementedError("Code not implemented")
	
	def search(self,element):
		raise NotImplementedError("Code not implemented")
	
	def indexOf(self,element):
		raise NotImplementedError("Code not implemented")
