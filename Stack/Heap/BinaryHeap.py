#coding: utf-8

__author__ = "Ionesio Junior"

class BinaryHeap():
	''' Heap is a priority queue data-structure in tree format.
		
	   Attributes:
		heapArray[elements] : store elements of data structure
		index(int) : index of last element stored in heap array
        '''
	__heapArray = None;
	__index = None;
	def __init__(self):
		''' Default heap constructor initialize your attributes '''
		self.__heapArray = []
		self.__index = -1
	
	def insert(self,element):
		''' Insert new element in last position of heap tree,after correct your position by value  (Max->root)
		
		   Args:
			element(optional) : element to be inserted
		'''
		if(element != None):
			self.__index = self.__index + 1
			self.__heapArray.insert(self.__index,element)
			i = self.__index
			while(i > 0 and self.__heapArray[self.__parent(i)] < self.__heapArray[i]):
				aux = self.__heapArray[i]
				self.__heapArray[i] = self.__heapArray[self.__parent(i)]
				self.__heapArray[self.__parent(i)] = aux
				i = self.__parent(i)
	
	def extractRoot(self):
		''' Remove  and return element in root position or raise exception if heap is empty
		
		   Returns:
			element(optional) :  root element to be removed
		
		   Raises:
			Exception : when heap is empty
		'''
		if(self.isEmpty()):
			raise Exception("Heap is Empty!!")
		else:
			removedElement = self.__heapArray[0]
			self.__heapArray[0] = self.__heapArray[self.__index]
			self.__index = self.__index - 1
			self.__heapify(self.__heapArray,0)
			return removedElement
	
	
	def rootElement(self):
		''' Return element in root position without removed it, or return None if heap is empty 
		
		    Returns:
			element(optional) : current root element / None
		'''
		if(self.isEmpty()):
			return None
		else:
			return self.__heapArray[0]
	
	
	def heapSort(self,array):
		'''
			Sort an list using heap structure (Destruction of current heap!)
		     
 			Args:
			    array[] : list to be sorted
			
  			Returns:
			    array[] : sorted list
		'''
		self.buildHeap(array)
		for i in range(self.__index,0,-1):
			aux = self.__heapArray[0]
			self.__heapArray[0] = self.__heapArray[i]
			self.__heapArray[i] = aux
			self.__index = self.__index - 1
			self.__heapify(self.__heapArray,0)
		return self.__heapArray
	
	
	def buildHeap(self,array):
		''' Build a heap structure using an list of elements (Destruction of current heap)
		
		
		   Args:
			array[] : list to construct a new heap
		'''
		self.__heapArray = []
		self.__index = -1
		for i in range(len(array)):
			self.insert(array[i])
	
	
	def toArray(self):
		''' Return all of heap elements in list structure
		
		    Returns:
			array[] : list of heap elements
                '''
		return self.__heapArray
	
	
	def size(self):
		''' Return how many elements have in current heap
		
		    Returns:
			size(int) : number of elements in heap structure
		
		'''
		return self.__index + 1
	
	def isEmpty(self):
		''' Return true if heap is empty or false,otherwise 
		
		    Returns:
			boolean
		'''
		return (self.__index  < 0)	

	############################	AUXILIAR METHODS	########################################################
	
	
	def __parent(self,index):
		''' Return parent of some node element
		
		    Args:
			index(int) :index of some node

		    Returns:
			index(int) : index of parent node
		'''
		return index / 2
	
	def __left(self,index):
		''' Return left son of some node element
		
		   Args:
			index(int) : index of some node
		   Retuns:
			index(int) : index of left son
		
		'''
		return (2 * index)
	
	def __right(self,index):
		''' Return right son of some node element
		
		    Args:
			index(int) : index of some node
		    Returns:
			index(int) :index of right son
		
		'''
		return (2 * index) + 1
	
	
	def __heapify(self,array,index):
		''' Correct node positions when some node is inserted or removed
		
		    Args:
			array[elements] = heap array
			index(int) = index of inserted/removed node
		'''
		left = self.__left(index)
		right = self.__right(index)
		largest = 0
		if(left <= self.__index and self.__heapArray[left] > self.__heapArray[index]):
			largest = left
		else:
			largest = index
		if(right <= self.__index and self.__heapArray[right] > self.__heapArray[largest]):
			largest = right
	
		if(largest != index):
			aux = self.__heapArray[index]
			self.__heapArray[index] = self.__heapArray[largest]
			self.__heapArray[largest] = aux
			self.__heapify(self.__heapArray,largest)

