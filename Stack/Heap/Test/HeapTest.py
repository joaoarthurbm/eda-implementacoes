import unittest
import sys,os
sys.path.append("../")
from BinaryHeap import BinaryHeap

__author__ = "Ionesio Junior"


class HeapTest(unittest.TestCase):

	def setUp(self):
		self.heap = BinaryHeap();
	
                empty = []
                odd = [9,8,7,6,5,4,3,2,1]
                even = [9,8,7,6,5,4,3,2,1,0]
                equal = [5,5,5,5,5,5,5]
                repeated = [9,8,5,8,7,8,5]
                ordered = [1,2,3,4,5,6,7,8,9]

                self.arrays = []
                self.arrays.append(empty)
                self.arrays.append(odd)
                self.arrays.append(even)
                self.arrays.append(equal)
                self.arrays.append(repeated)
                self.arrays.append(ordered)

	
	def testIsEmpty(self):
		self.assertEqual(True,self.heap.isEmpty())
		self.heap.insert(10)
		self.assertEqual(False,self.heap.isEmpty())
		self.heap.extractRoot()
		self.assertEqual(True,self.heap.isEmpty())

	
	def testSize(self):
		self.assertEqual(0,self.heap.size())
		self.assertEqual(True,self.heap.isEmpty())
		
		for i in range(10):
			self.heap.insert(i)
		
		self.assertEqual(10,self.heap.size())
		
		for i in range(10,20,2):
			self.heap.insert(i)
		
		self.assertEqual(15,self.heap.size())
		
		for i in range(5):
			self.heap.extractRoot()
		
		self.assertEqual(10,self.heap.size())

			
	def testInsert(self):
		#Test None
		self.assertEqual(True,self.heap.isEmpty())
		self.heap.insert(None)
		self.assertEqual(True,self.heap.isEmpty())

		#Test equal values
		self.heap.insert(10)
		self.assertEqual(1,self.heap.size())
		self.heap.insert(10)
		self.assertEqual(2,self.heap.size())

	
	def testExtractRootElement(self):
		#without equal values
		self.assertEqual(True,self.heap.isEmpty())
		for i in range(100):
			self.heap.insert(i)
		
		for i in range(99,-1,-1):
			self.assertEqual(i,self.heap.extractRoot())

		self.assertEqual(True,self.heap.isEmpty())


		#with equal values
		for i in range(10):
			if( i < 5):
				self.heap.insert(10)
			else:
				self.heap.insert(20)
		
		for i in range(10):
			if(i < 5):
				self.assertEqual(20,self.heap.extractRoot())
			else:
				self.assertEqual(10,self.heap.extractRoot())
		
		self.assertEqual(True,self.heap.isEmpty())


	def testRootElement(self):
		self.assertEqual(True,self.heap.isEmpty())
		
		for i in range(10):
			self.heap.insert(i)
		
		self.assertEqual(10,self.heap.size())
		
		for i in range(10):
			self.assertEqual(9,self.heap.rootElement())
		
		self.assertEqual(10,self.heap.size())
		self.assertEqual(False,self.heap.isEmpty())

	def testHeapSort(self):
		for i in range(len(self.arrays)):
			copy = self.arrays[i][:]
			copy.sort()
			self.assertEqual(copy,self.heap.heapSort(self.arrays[i]))	


	def testBuildHeap(self):
		#Empty array
		self.heap.buildHeap(self.arrays[0]);
		self.assertEqual(len(self.arrays[0]),self.heap.size());
		self.assertEqual(None,self.heap.rootElement());

		#Other cases
		for i in range(1,len(self.arrays)):
			self.heap.buildHeap(self.arrays[i])
			self.assertEqual(len(self.arrays[i]),self.heap.size())
			self.assertEqual(max(self.arrays[i]),self.heap.rootElement())	



	def testToArray(self):
		results = []
		results.append([])
		results.append([9,8,7,6,5,4,3,2,1])
		results.append([9,8,7,6,5,4,3,2,1,0])
		results.append([5,5,5,5,5,5,5])
		results.append([9,8,8,8,5,7,5])
		results.append([9,8,7,6,4,3,2,5,1])
	
		for i in range(len(self.arrays)):
			self.heap.buildHeap(self.arrays[i])
			self.assertEqual(results[i],self.heap.toArray())

if __name__ == "__main__":
	unittest.main()	
