#coding : utf-8
import sys
from Node import Node


__author__ = "Ionesio Junior"

class SkipList(object):
	''' Skip list class implementation
		
	    Attributes:
		height(int) : current max height
		head(Node) : head of skiplist
		tail(Node) : tail of skiplist
	'''	
	def __init__(self,maxHeight):
		''' Skip list constructor , initialize attributes and connect head to tail 
		
		    Args:
			maxHeight(int) : initial maxheight
		'''
		self.__height = maxHeight
		self.__head = Node(-1 * sys.maxint,maxHeight,None)
		self.__tail = Node(sys.maxint,maxHeight,None)
		self.__connectHeadToTail()


	def __connectHeadToTail(self):
		for i in range(self.__height):
			self.__head.setForwardNode(i,self.__tail)
	
	
	def insert(self,key,data,height):
		''' Insert some element in skiplist structure (not allowed None key,data or height values)
		
		    Args:
			key(optional) : key to determine the position of element in skiplist structure
			data(optional) : data to store in skip list structure
			height(int) : height of node inserted in skiplist structure 
		
		'''
		if(data != None and key != None and height != None):
			self.__ajustHeight(height)
			previousNodes = [None] * height
			aux = self.__head
			for i in range(height - 1,-1,-1):
				while(aux.getForwardNode(i).getValue() != None and aux.getForwardNode(i).getKey() < key):
					aux = aux.getForwardNode(i)
				
				previousNodes[i] = aux
			aux = aux.getForwardNode(0)
			if(aux.getKey() == key):
				aux.setValue(data)
			else:
				aux = Node(key,height,data)
				self.__changePointers(height,previousNodes,aux)

	
	def __changePointers(self,height,previousNodes,aux):
		for i in range(height):
			if(previousNodes[i].getForwardNode(i) == None):
				aux.setForwardNode(i,self.__tail)
			else:
				aux.setForwardNode(i,previousNodes[i].getForwardNode(i))
				previousNodes[i].setForwardNode(i,aux)


	def __ajustHeight(self,height):
		if(height > self.__height):
			self.__tail.setForward([None] * height)
			newForward = [None] * height
			for i in range(height):
				if(i < self.__height):
					newForward[i] = self.__head.getForwardNode(i)
				else:
					newForward[i] = self.__tail
			self.__height = height
			self.__head.setForward(newForward)
	
	
	def remove(self,key):
		''' Search an element and remove it
		
		    Args:
			key(optional) : key of element to be removed
		
		'''
		array  = [None] * self.__height
		aux = self.__head
		for i in range(self.__height - 1, -1,-1):
			while(aux.getForwardNode(i).getValue() != None and aux.getForwardNode(i).getKey() < key):
				aux = aux.getForwardNode(i)
			array[i] = aux
		aux = aux.getForwardNode(0)
		if(aux.getKey()  == key):
			for i in range(self.__height):
				if(array[i].getForwardNode(i).getKey() != aux.getKey()):
						break;
				array[i].setForwardNode(i,aux.getForwardNode(i))


	def search(self,key):
		''' Search an element and return it, if not found  return None
			
		   Args:
			key(optional) : key of element to be searched
		
		   Returns:
			aux(Node/None) : Node with that key
		
		'''
		aux = self.__head
		for i in range(self.__height - 1,-1,-1):
			while(aux.getForwardNode(i).getValue() != None and aux.getForwardNode(i).getKey() < key):
				aux = aux.getForwardNode(i)
		aux = aux.getForwardNode(0)
		if(aux.getKey() == key):
			return aux
		else:
			return None


	def size(self):
		''' Return how many element have in skip list structure (without head and tail)
		
		
		   Returns:
			count(int) : number of elements inside the skiplist structure
		'''
		count = 0
		aux = self.__head.getForwardNode(0)
		while(aux.getKey() != self.__tail.getKey()):
			count += 1
			aux = aux.getForwardNode(0)
		return count


	def toList(self):
		''' Return list structure with tuples(data,key,height) of all nodes inside the skiplist (including head and tail)
		
		    Returns:
			nodeList[] : tuple(data,key,height) list
		'''
		size = self.size() + 2
		nodeList = []
		index = 0
		aux = self.__head
		while(index < size):
			nodeList.append((aux.getValue(),aux.getKey(),aux.height()))
			aux = aux.getForwardNode(0)
			index += 1
		return nodeList

	
	def height(self):
		''' Return current max height in nodes inside skiplist (without head/tail height)
		
		    Returns:
			height(int) : max height of some node
		'''
		height = self.__height - 1
		while (height >= 0 and self.__head.getForwardNode(height).getValue() == None):
			height -= 1
		return height

