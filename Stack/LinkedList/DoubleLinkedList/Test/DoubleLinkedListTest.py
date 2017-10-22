#coding: utf-8

import sys,os
import unittest
sys.path.append("../")

from DoubleLinkedList import DoubleLinkedList
from RecursiveDoubleLinkedList import RecursiveDoubleLinkedList
from random import randint

class DoubleLinkedListTest(unittest.TestCase):
	
	def setUp(self):
		self.list1 = DoubleLinkedList()
		self.list2 = RecursiveDoubleLinkedList()

	def testIsEmpty(self):
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
	
		self.list1.insert(3)
		self.list2.insert(3)
		
		self.assertEqual(False,self.list1.isEmpty())
		self.assertEqual(False,self.list2.isEmpty())
	
		self.list1.removeFirst()
		self.list2.removeFirst()
		
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())	
		
		self.list1.insert(10)
		self.list2.insert(10)
	
		self.assertEqual(False,self.list1.isEmpty())
		self.assertEqual(False,self.list2.isEmpty())
	
		self.list1.removeLast()
		self.list2.removeLast()
		
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
		
		self.list1.insert(15)
		self.list2.insert(15)
		
		self.assertEqual(False,self.list1.isEmpty())
		self.assertEqual(False,self.list2.isEmpty())
		
		self.list1.remove(15)
		self.list2.remove(15)
	
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
	
	def testSize(self):
		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list2.size())

		for i in range(20):
			self.list1.insert(i)
			self.list2.insert(i)
			self.assertEqual(i + 1,self.list1.size())
			self.assertEqual(i + 1,self.list2.size())
		
		size = self.list1.size()
		
		for i in range(20):
			self.list1.removeFirst()
			self.list2.removeLast()
			size -= 1
			self.assertEqual(size,self.list1.size())
			self.assertEqual(size,self.list2.size())
		
		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list2.size())
	
	def testSearch(self):
		self.assertEqual(None,self.list1.search(None))
		self.assertEqual(None,self.list2.search(None))

		for i in range(1000):
			self.assertEqual(None,self.list1.search(randint(-1000,10000)))
			self.assertEqual(None,self.list2.search(randint(-1000,10000)))

		for i in range(1000):
			if(i < 20):
				self.list1.insert(i)
				self.list2.insert(i)
				self.assertEqual(i,self.list1.search(i))
				self.assertEqual(i,self.list2.search(i))
			else:
				self.assertEqual(None,self.list1.search(i))
				self.assertEqual(None,self.list2.search(i))
		for i in range(20):
			self.list1.removeLast()
			self.list2.removeFirst()
		
		for i in range(20):
			self.assertEqual(None,self.list1.search(i))
			self.assertEqual(None,self.list2.search(i))			
	
	def testInsert(self):
		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list2.size())
		#Insert None element
		self.list1.insert(None)
		self.list2.insert(None)
	
		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list1.size())
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
		#Insert 0-49 element
		for i in range(50):
			self.list1.insert(i)
			self.list2.insert(i)
		
		self.assertEqual(50,self.list1.size())
		self.assertEqual(50,self.list2.size())
	 	
		#Inserted element repeated
		self.list1.insert(15)
		self.list2.insert(15)
		self.assertEqual(51,self.list1.size())
		self.assertEqual(51,self.list2.size())


	def testRemove(self):
		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list2.size())
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
		
		for i in range(100):
			self.list1.insert(i)
			self.list2.insert(i)
		
		self.assertEqual(100,self.list1.size())
		self.assertEqual(100,self.list2.size())
		
		for i in range(50):
			self.list1.remove(i)
			self.list2.remove(i)
		
		self.assertEqual(50,self.list1.size())
		self.assertEqual(50,self.list2.size())
		
		for i in range(100):
			if(i < 50):
				self.assertEqual(None,self.list1.search(i))
				self.assertEqual(None,self.list2.search(i))
			else:
				self.assertEqual(i,self.list1.search(i))
				self.assertEqual(i,self.list1.search(i))
		
	
	def testToArray(self):
		testArray = [randint(0,1000) for x in range(30)]
		for j in range(len(testArray)):
			self.list1.insert(testArray[j])
			self.list2.insert(testArray[j])
		self.assertEqual(testArray,self.list1.toArray())
		self.assertEqual(testArray,self.list2.toArray())
	
	def testInsertFirst(self):
		self.assertEqual(0,self.list1.size())
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(0,self.list2.size())
		self.assertEqual(True,self.list2.isEmpty())
		
		for i in range(100):
			self.list1.insertFirst(i)
			self.list2.insertFirst(i)
			self.assertEqual(i+1,self.list1.size())
			self.assertEqual(i + 1,self.list2.size())
			self.assertEqual(None,self.list1.search(i+ 1))
			self.assertEqual(None,self.list2.search(i+1))
		
		resultList = range(99,-1,-1)
		self.assertEqual(resultList,self.list1.toArray())
		self.assertEqual(resultList,self.list2.toArray())
		
	def removeFirst(self):
		testArray = [randint(0,1000) for i in range(30)]
		for i in range(len(testArray)):
			self.list1.insert(testArray[i])
			self.list2.insert(testArray[i])
		
		size = self.list1.size()

		for i in range(len(testArray)):
			self.list1.removeFirst()
			self.list2.removeFirst()
			size -= 1
			self.assertEqual(None,self.list1.search(testArray[i]))
			self.assertEqual(None,self.list2.search(testArray[i]))
			self.assertEqual(size,self.list1.size())
			self.assertEqual(size,self.list2.size())


		self.assertEqual(0,self.list1.size())
		self.assertEqual(0,self.list2.size())

		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())
			
	
	def removeLast(self):
		testArray = [randint(0,1000) for i in range(30)]
		for i in range(len(testArray)):
			self.list1.insert(testArray[i])
			self.list2.insert(testArray[i])

		for i in range(29,-1,-1):
			self.list1.removeLast()
			self.list2.removeLast()
			self.assertEqual(None,self.list1.search(testArray[i]))
			self.assertEqual(None,self.list2.search(testArray[i]))
			self.assertEqual(i,self.list1.size())
			self.assertEqual(i,self.list2.size())
		
		self.assertEqual(True,self.list1.isEmpty())
		self.assertEqual(True,self.list2.isEmpty())

if __name__ == "__main__":
	unittest.main()
