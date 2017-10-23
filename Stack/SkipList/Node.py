#coding: utf-8


__author__ = "Ionesio Junior"


class Node(object):
	''' Node class implementation for skip list structure
	   
            Attributes:
		data(optional) : data value to be stored in this node
		key(optional) : key determine the position of node in skiplist structure
		forward[Node] : list with all of possible neighbors
	'''
		
	__data = None
	__key = None
	__forward = None
	def __init__(self,key,height,data):
		''' Node constructor,initalize attributes 
		
		    Args:
			key(optional) : key value of this node
			height(int) : number of neighbors of this node
			data(optional) : data value stored in this node
		'''
		self.__data = data
		self.__key = key
		self.__forward = [None] * height
	
	def height(self):
		''' Get height of forward attribute
		
		    Returns:
			height(int) : height of forward list
		'''
		return len(self.__forward)
	
	def getValue(self):
		''' Return data value of this node
		 	
		   Returns:
			data(optional) : return data value of this node
		'''
		return self.__data
		
	def setValue(self,newValue):
		''' Change data value of this node
		
		
		   Args:
			newDataValue(optional) : new data value of this node
		'''
		self.__data = newValue
	
	def getKey(self):
		''' Return key of this node
		
		    Returns:
			key(optional) : return key of this node 
		
		'''
		return self.__key
	
	def setKey(self,newKey):
		''' Change key value of this node
		
		    Args:
			newKey(optional) : new value of key	
		'''
		self.__key = newKey
	
	def getForward(self):
		''' Return forward list of this node
		
		    Returns:
			forward[Nodes] : return forward list of this node
		'''
		return self.__forward
	
	def getForwardNode(self,level):
		''' Return  node of forward list in specific position 
		
		    Args:
			level(int) : specific position of node
		    Returns:
			Node : node in specific position	
		'''
		return self.__forward[level]
	
	def setForwardNode(self,level,newNode):
		''' Change node of forward list in specific position
		
		    Args:
			level(int) : specific position
			newNode(Node) : node to be changed
		'''
		self.__forward[level] = newNode

	def setForward(self,newForward):
		''' Change all forward List 
		
		   Args:
			newForward[] : new forward list
		'''
		self.__forward = newForward
