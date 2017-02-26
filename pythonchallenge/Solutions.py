'''
Created on Jan 17, 2015

@author: radovan
'''
from string import maketrans, punctuation, whitespace, translate

def messageDecoder(message):
    intab = "abcdefghijklmnopqrstuvwxyz"
    outtab = "cdefghijklmnopqrstuvwxyzab"
    transtab = maketrans(intab, outtab)
    return message.translate(transtab)

def rareCharacters(message):
    deleteChars = whitespace + punctuation
    return translate(message, None, deleteChars)

if __name__ == '__main__':
    print messageDecoder("map")
    print rareCharacters("equality")