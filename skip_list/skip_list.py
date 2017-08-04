import sys
import random

# toss a coin to decide the level
def get_level():
    coin = random.randint(1, 100)
    level = 0

    while coin <= 50:
        level += 1
        coin = random.randint(1, 100)

    return level

class Node:

    def __init__(self, value, level):
        self.value = value
        self.forward = (level+1) * [None]
        self.level = level

    def __str__(self):
        return str(self.value)

class SkipList:

    def __init__(self, max_level):
        self.max_level = max_level
        self.head = Node(-sys.maxint - 1, max_level)
        self.tail = Node(sys.maxint, max_level)

        # connect head and tail
        for i in range(self.head.level, -1, -1):
                self.head.forward[i] = self.tail

    # returns the path of the search: a list of tuples where the index is the level
    # and the tuple is the relationship t[0] -> t[1]
    # 
    def search(self, value):
        current_max_level = self.head.level
        path = (self.head.level + 1) * [None]
        aux = self.head
        
        while True:
            # saves path: at level current_max_level aux points to aux.forward[current_max_level]
            path[current_max_level] = (aux, aux.forward[current_max_level])
            
            # found
            if aux.forward[current_max_level].value == value:
                return [ p for p in path if p != None]

            # skip
            if aux.forward[current_max_level].value < value:
                aux = aux.forward[current_max_level]
            
            # check level below    
            else:
                current_max_level -= 1

            # not in the list
            if current_max_level == -1:
                return [ p for p in path if p != None]

    # accepting pre-defined levels enables testing.
    def add(self, value, level = None):
        if not level:
            level = min(get_level(), self.max_level)
        
        newNode = Node(value, level)
        path = self.search(value)

        # sets forwards
        for i in range(level, -1, -1):
            prev = path[i][0]
            nextt = path[i][1]
            prev.forward[i] = newNode
            newNode.forward[i] = nextt

    def contains(self, value):
        path = self.search(value)
        return path[0][1].value == value
        

    def remove(self, value):
        path = self.search(value)

        ## if contains
        if path[0][1].value == value:
            to_remove = path[0][1]

            # set forwards
            for level in range(to_remove.level + 1):
                prev = path[level][0]
                prev.forward[level] = to_remove.forward[level]

            return True
        
        return False

    def str_rep(self):
        all_levels = []
        for i in range(self.head.level, -1, -1):

            l = []
            l.append(self.head.value)
            aux = self.head
            while aux.forward[i] != None and aux.forward[i] != self.tail:
                l.append(aux.forward[i].value)
                aux = aux.forward[i]
            
            if aux.forward[i] == self.tail:
                l.append(self.tail.value)

            all_levels.append(l)
        
        return all_levels
