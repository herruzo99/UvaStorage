# -*- coding=utf-8 -*-
import sys

if len(sys.argv)>1:
	s=0
	for i in range(1,len(sys.argv)):
		s +=int( sys.argv[i])
	print s
else:
	print"argumentos"
