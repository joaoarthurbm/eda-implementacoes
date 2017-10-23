#coding: utf-8

import sys,os
sys.path.append("../")
from SkipList import SkipList
from Node import Node
import unittest
from random import randint


__author__ = "Ionesio Junior"

class SkipListTest(unittest.TestCase):
	
	def setUp(self):
		self.skip1 = SkipList(5)
		self.skip2 = SkipList(3)
		self.elementList = ["A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T"]
	
	def testSize(self):
		self.assertEqual(0,self.skip1.size())
		self.assertEqual(0,self.skip2.size())
		
		keyList = range(20);
		heightList = [randint(1,5) for i in range(20)]
		
		for i in range(20):
			if(i < 10):
				self.skip1.insert(keyList[i],self.elementList[i],heightList[i])
				self.skip2.insert(keyList[i],self.elementList[i],heightList[i])
			else:
				self.skip2.insert(keyList[i],self.elementList[i],heightList[i])
		
		self.assertEqual(10,self.skip1.size())
		self.assertEqual(20,self.skip2.size())

		#insert repeated key
		self.skip1.insert(5,self.elementList[3],heightList[9])
		self.assertEqual(10,self.skip1.size())
		
		#test remove elements
		for i in range(10):
			self.skip1.remove(keyList[i])
			self.skip2.remove(keyList[i])
		
		self.assertEqual(0,self.skip1.size())
		self.assertEqual(10,self.skip2.size())


	def testInsert(self):
		self.assertEqual(0,self.skip1.size())
		self.assertEqual(0,self.skip2.size())
		
		self.skip1.insert(randint(0,100),None,randint(1,100))
		self.skip2.insert(randint(0,100),None,randint(1,100))
		
		self.skip1.insert(None,self.elementList[randint(0,19)],randint(1,100))
		self.skip2.insert(None,self.elementList[randint(0,19)],randint(1,100))
	
		self.skip1.insert(randint(0,100),self.elementList[randint(0,19)],None)
		self.skip2.insert(randint(0,100),self.elementList[randint(0,19)],None)
		
		self.assertEqual(0,self.skip1.size())
		self.assertEqual(0,self.skip2.size())
		
		#Bruteforce test
		for i in range(1000):
			self.skip1.insert(randint(0,1000),self.elementList[randint(0,19)],randint(1,1000))
			self.skip2.insert(randint(0,1000),self.elementList[randint(0,19)],randint(1,1000))

	
	def testRemove(self):
		#Remove some key in empty skiplist
		self.skip2.remove(randint(0,1000))
		self.skip1.remove(randint(0,1000))
			
		self.skip1.insert(20,self.elementList[randint(0,19)],randint(1,100))
		self.skip2.insert(20,self.elementList[randint(0,19)],randint(1,100))
		
		#Remove None key
		self.skip1.remove(None)
		self.skip2.remove(None)
	
		self.assertEqual(1,self.skip1.size())
		self.assertEqual(1,self.skip2.size())

		for i in range(20):
			self.skip1.insert(i,self.elementList[randint(0,19)],randint(1,100))
			self.skip2.insert(i,self.elementList[randint(0,19)],randint(1,100))
		
		#Bruteforce test	
		for i in range(1000):
			self.skip1.remove(i)
			self.skip2.remove(i)
		
		self.assertEqual(0,self.skip1.size())
		self.assertEqual(0,self.skip2.size())
	
	
	def testSearch(self):
		#Search in empty skiplist
		self.assertEqual(None,self.skip1.search(randint(-100,10000)))
		self.assertEqual(None,self.skip2.search(randint(-100,10000)))
		
		#Search None key
		self.assertEqual(None,self.skip1.search(None))
		self.assertEqual(None,self.skip1.search(None))
		
		for i in range(20):
			self.skip1.insert(i,self.elementList[randint(0,19)],randint(1,100))
			self.skip2.insert(i,self.elementList[randint(0,19)],randint(1,100))
		
		foundElementsCount = 0
		for i in range(-100,1001,1):
			if(self.skip1.search(i) != None  and self.skip2.search(i) != None):
				foundElementsCount += 1
	
		self.assertEqual(20,foundElementsCount)
	
	
	def testToList(self):
		height = range(1,21)
		for i in range(20):
			self.skip1.insert(i,self.elementList[i],height[i])
	
		listResult = self.skip1.toList()
		
		#Head
		self.assertEqual(None,listResult[0][0])
		self.assertEqual(-1 * sys.maxint,listResult[0][1])
		self.assertEqual(20,listResult[0][2])
		
		#Tail
		self.assertEqual(None,listResult[21][0])
		self.assertEqual(sys.maxint,listResult[21][1])
		self.assertEqual(20,listResult[21][2])
		
	
		#Other elements
		for i in range(1,21,1):
			self.assertEqual(i-1,listResult[i][1])
			self.assertEqual(self.elementList[i - 1],listResult[i][0])
			self.assertEqual(i,listResult[i][2])
	
	def height(self):
		self.assertEqual(5,self.skip1.height())
		self.assertEqual(3,self.skip2.height())
		
		for i in range(1,21,1):
			self.skip1.insert(i,self.elementList[randint(0,19)],i)
			self.skip2.insert(i,self.elementList[randint(0,19)],i)
			if(i <= 3):
				self.assertEqual(3,self.skip2.height())
				self.assertEqual(5,self.skip1.height())
			elif(i <= 5):
				self.assertEqual(i,self.skip2.height())
				self.assertEqual(5,self.skip1.height())
			else:
				self.assertEqual(i,self.skip2.height())
				self.assertEqual(i,self.skip1.height())


if __name__ == "__main__":
	unittest.main()
