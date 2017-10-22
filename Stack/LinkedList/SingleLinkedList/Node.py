#coding: utf-8


__author__ = "Ionésio Junior"

class Node():
	''' Node class implementation used in Iterative Single Linked List implementation

	    Attributes:
		nextNode(Node) : next node in linked list
		data(optional) : data stored in this node
	'''	
	__nextNode = None;
	__data = None;
	def __init__(self,nextNode = None,data = None):
		''' Constructor of Node class
			
		    Agrs: 
			nextNode(Node) : next node in linked list, Default to None.
			data(optional) : data to store in this node, Default to None.
		'''
		self.__nextNode = nextNode
		self.__data = data
	
	
	def isEmpty(self):
		''' Return true if data of this node is None or false,otherwise.
			
		    Returns:
			boolean
		'''
		return (self.__data == None)
	
	
	def getNext(self):
		''' Return next node in linked list.
			
		    Return:
			next(Node): next node in linked list.
		
		'''
		return self.__nextNode
	
	def setNext(self, nextNode):
		''' Change value of attribute "nextNode"
		
		    Args:
			nextNode (Node) : new next node.
		'''
		self.__nextNode = nextNode
	
	def getData(self):
		''' Return data stored in this node.
			
		    Returns:
			data(optional) : data stored in this node.
		
		'''
		return self.__data
	
	def setData(self,newData):
		''' Change value of data stored in this node.
		
		    Args:	
			newData(optional) : new data value.
		'''
		self.__data = newData
