#coding: utf-8
import sys,os
import unittest
sys.path.append("../")
sys.path.append("../Method/")
sys.path.append("../Operation/")

from Operation import *
from Methods import *
from OpenAdress import OpenAdress
from ClosedAdress import ClosedAdress


__author__ = "Ionesio Junior"


class HashTableTest(unittest.TestCase):
	
	def setUp(self):
		self.open = OpenAdress(10,Method.LINEAR_PROBING,Operation.MULTIPLICATION)
		self.closed = ClosedAdress(10,Operation.DIVISION)


	def testIsEmpty(self):
		pass
		self.assertEqual(True,self.open.isEmpty())
		self.assertEqual(True,self.closed.isEmpty())

		self.open.insert(10)
		self.closed.insert(10)
		
	
		self.assertEqual(False,self.open.isEmpty())
		self.assertEqual(False,self.closed.isEmpty())
	
		self.open.remove(10)
		self.closed.remove(10)
	
		self.assertEqual(True,self.open.isEmpty())
		self.assertEqual(True,self.closed.isEmpty())
		
	def testIsFull(self):
		pass
		self.assertEqual(True,self.open.isEmpty())
		self.assertEqual(False,self.open.isFull())
		
		for i in range(10):
			self.open.insert(i)
			self.closed.insert(i)
		
		self.assertEqual(True,self.closed.isFull())	
		self.assertEqual(True,self.open.isFull())
	
	def testCapacity(self):
		pass
		self.assertEqual(10,self.open.capacity())
		self.assertEqual(10,self.open.capacity())

	def testSize(self):
		for i in range(10):
			self.assertEqual(i,self.closed.size())
			self.open.insert(i)
			self.closed.insert(i)
	
	def testGetCOLLISIONS(self):
		for i in range(10):
			self.open.insert(i)

		self.assertEqual(1,self.open.getCOLLISIONS())
		self.assertEqual(True,self.open.isFull())
		
		self.open = OpenAdress(20,Method.LINEAR_PROBING,Operation.MULTIPLICATION)
		for i in range(20):
			self.open.insert(i)

		self.assertEqual(11,self.open.getCOLLISIONS())
	
	def testInsert(self):
		#Repeated elements
		for i in range(1000):
			self.open.insert(1)
			self.closed.insert(1)
		self.assertEqual(1,self.open.size())
		self.assertEqual(1,self.closed.size())

		#None elements
		for i in range(1000):
			self.open.insert(None)
			self.closed.insert(None)
		self.assertEquals(1,self.open.size())
		self.assertEqual(1,self.closed.size())
	
	def testRemove(self):
		self.assertEqual(True,self.open.isEmpty())
		self.assertEqual(True,self.closed.isEmpty())
		
		for i in range(10):
			self.open.insert(i)
			self.closed.insert(i)
		
		self.assertEqual(10,self.open.size())
		self.assertEqual(10,self.closed.size())
		self.assertEqual(True,self.open.isFull())
		self.assertEqual(True,self.closed.isFull())
		
		self.open.remove(20)
		self.closed.remove(20)
		self.open.remove(None)
		self.closed.remove(None)
		
		self.assertEqual(10,self.open.size())
		self.assertEqual(10,self.closed.size())
		self.assertEqual(True,self.open.isFull())
		self.assertEqual(True,self.closed.isFull())

		self.open.remove(9)
		self.closed.remove(9)
	
		self.assertEqual(9,self.open.size())
		self.assertEqual(9,self.closed.size())
		
		self.assertEqual(None,self.open.search(9))
		self.assertEqual(None,self.closed.search(9))

		for i in range(9):
			self.open.remove(i)
			self.closed.remove(i)
		
		
		self.assertEqual(True,self.open.isEmpty())
		self.assertEqual(True,self.closed.isEmpty())
		self.assertEqual(0,self.open.size())
		self.assertEqual(0,self.closed.size())
	
		
	
	def testSearch(self):
		for i in range(10):
			self.open.insert(i)
			self.closed.insert(i)
		
		self.assertEqual(None,self.open.search(None))
		self.assertEqual(None,self.closed.search(None))
		
	
		for i in range(20):
			if(i < 10):
				self.assertEqual(i,self.open.search(i))
				self.assertEqual(i,self.closed.search(i))
			else:
				self.assertEqual(None,self.open.search(i))
				self.assertEqual(None,self.closed.search(i))
			
		for i in range(10):
			self.open.remove(i)
			self.closed.remove(i)
			self.assertEqual(None,self.open.search(i))
			self.assertEqual(None,self.closed.search(i))
	
	def testIndexOf(self):
		for i in range(10):
			self.closed.insert(i)
			self.open.insert(i)
		
		previousCalcIndexOpen = [0,6,2,8,4,1,7,3,9,5]
		previousCalcIndexClosed = [(0,0) ,(1,0) , (2,0) , (3,0) ,(4,0) ,(5,0), (6,0),(7,0) , (8,0) ,(9,0)]
		
		for i in range(10):
			self.assertEqual(previousCalcIndexOpen[i],self.open.indexOf(i))
			self.assertEqual(previousCalcIndexClosed[i],self.closed.indexOf(i))
		

if __name__ == "__main__":
	unittest.main()
