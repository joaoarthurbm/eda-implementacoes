#coding: utf-8
import sys,os
sys.path.append('/Operation/')
from Hashtable import Hashtable
from Operation import Operation

__author__ = "Ionesio Junior"


class ClosedAdress(Hashtable):
	''' Hashtable closed adress implementation (internal table is a matrix of objects) '''	
	def __init__(self,tableSize,operationMethod):
		''' Hash table closed adress constructor , initialize Hashtable attributes '''
		Hashtable.__init__(self,tableSize,operationMethod)

	
	def insert(self,element):
		''' Insert an element into the internal table or raise Exception if table is full
		    (not allowed None elements or element equal other inserted previously)

		    Args:
			element(optional) : element to be inserted
 		    Raises:
			Exception : if internal table is full
		'''
		if(self.isFull()):
			raise Exception("HashtableOverflowException")
		else:
			if(element == None):
				return
			else:
				hashKey = self.function.hashFunc(element)
				if(self.table[hashKey] == None):
						self.table[hashKey] = []
				if(element in self.table[hashKey]):
					return
				else:
					self.table[hashKey].append(element)
					self.elements += 1


	def remove(self,element):
		''' Remove some element into the internal table
			
		    Args:
			element(optional) : to be removed
		'''
		if(self.isEmpty() or element == None):
			return
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return
			else:
				try:
					self.table[hashKey].remove(element)
					self.elements -= 1
				except:
					return
	
	
	def search(self,element):
		''' Search some element in internal table and return it,if not found return None
		
		    Args:
			element(optional) : element to be searched
			
		    Returns:
			element(optional/None) : element found / None
		'''
		if(element == None):
			return None
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return None
			else:
				try:
					index = self.table[hashKey].index(element)
					return self.table[hashKey][index]
				except:
					return None
	

	def indexOf(self,element):
		'''
			Return table index of a found element, or None if element not found
			
			Args:
				element(optional) : search index of this element
				
			Returns:
				index(int/None) : return index element / None 
		'''
		if(element == None):
			return -1
		else:
			hashKey = self.function.hashFunc(element)
			if(self.table[hashKey] == None):
				return -1
			else:
				try:
					return (hashKey,self.table[hashKey].index(element))
				except:
					return -1
