#coding: utf-8

__author__ = "Ionesio Junior"


class DoubleNode(object):
	'''Double Node class implementation used in iterative Double Linked List
		
	   Attributes:
		data(optional) : data stored in this node
		previous(Node) : previous Node
		nextNode(Node) : next Node
	'''
	
	__data = None;
	__previous = None;
	__nextNode = None;


	def __init__(self,data=None,previous=None,nextNode=None):
		''' Constructor of Double Node class, initialize all attributes.
		
		     Args:
			data(optional) : value of data stored in this node.Default to None
			previous(DoubleNode) : previous node to this
			nextNode(DoubleNode) : next node
		'''
		self.__data = data
		self.__previous = previous
		self.__nextNode = nextNode
	
	
	def isEmpty(self):
		''' Return true if this data is None or false,otherwise 
		
		   Returns:
			boolean
		'''
		return (self.__data == None)
	
	
	def getData(self):
		''' Return this data value
		
		    Returns:
			data(optional) : data value of this node.
		'''
		return self.__data
	
	
	def setData(self,newData):
		''' Change data value of this node.
		
		
		    Agrs:
			newData(optional) : new data value
		'''
		self.__data = newData

	
	def getNext(self):
		''' Return the next node.
			
		    Return:
			nextNode(DoubleNode) : next Double Node.
		
		'''
		return self.__nextNode
	
	
	def setNext(self,newNext):
		''' Change value of next Node.
		
		    Args:
			newNext(DoubleNode) : new next Node
		
		'''
		self.__nextNode = newNext
	
	
	def getPrevious(self):
		''' Return previous node value
		
		    Returns:
			previous(DoubleNode) : previousNode value
		
		'''
		return self.__previous
	
	
	def setPrevious(self,newPrevious):
		''' Change value of previous Node.
		
		    Args:
			newPrevious(DoubleNode) : new previousNode.
		
		'''
		self.__previous = newPrevious
	
	
		
		
