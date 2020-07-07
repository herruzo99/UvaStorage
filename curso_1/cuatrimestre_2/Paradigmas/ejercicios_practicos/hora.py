# -*- coding=utf-8 -*-
print "Dime la hora en segundos"
segundo = int(raw_input())
minutos=segundo/60
segundo = segundo-minutos*60
horas=minutos/60
minutos=minutos-horas*60
print "Han pasado %d:%d:%d" % (horas,minutos,segundo)
