

(deftemplate oavc-u "Plantilla univaluado"
	(slot objeto (type SYMBOL))
	(slot atributo(type SYMBOL))
	(slot valor)
	(slot factor (type FLOAT) (range -1.0 +1.0))
)
(deftemplate oavc-m "Plaintilla multivaluado"
	(slot objeto (type SYMBOL))
	(slot atributo(type SYMBOL))
	(slot valor)
	(slot factor (type FLOAT) (range -1.0 +1.0))
)

(defrule duplicar-hechos
	(declare (salience 10000))
=>
	(set-fact-duplication TRUE))

(defrule garantizar-univaluados
	(declare (salience 9000))
	?f1 <- (oavc-u (objeto ?o1) (atributo ?a1))
	?f2 <- (oavc-u (objeto ?o1) (atributo ?a1))
	(test (neq ?f1 ?f2))
=>
	(retract ?f2)
)

(defrule acumula-positivos-univaluados
	(declare (salience 10000))
	?fact1 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(>= ?f1 0)&:(< ?f1 1)))
	?fact2 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(>= ?f2 0)&:(< ?f2 1)))
	(test (neq ?fact1 ?fact2))
=>
	(retract ?fact1)
	(bind ?f3 (+ ?f1 (* ?f2 (- 1 ?f1))))
	(modify ?fact2 (factor ?f3)))

(defrule acumula-negativos-univaluados
	(declare (salience 10000))
	?fact1 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(<= ?f1 0)&:(> ?f1 -1)))
	?fact2 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(<= ?f2 0)&:(> ?f2 -1)))
	(test (neq ?fact1 ?fact2))
=>
	(retract ?fact1)
	(bind ?f3 (+ ?f1 (* ?f2 (+ 1 ?f1)))) 
	(modify ?fact2 (factor ?f3)))
	
(defrule acumula-positivoYnegativo-univaluados
	(declare (salience 10000))
	?fact1 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(>= ?f1 0)&:(< ?f1 -1)))
	?fact2 <- (oavc-u (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(<= ?f2 0)&:(> ?f2 -1)))
	(test (neq ?fact1 ?fact2))
=>
	(retract ?fact1)
	(bind ?f3 (/ (+ ?f1 ?f2) (- 1 (min (abs ?f1) (abs ?f2))))) 
	(modify ?fact2 (factor ?f3)))
	
(defrule acumula-positivos-multivaluados
	(declare (salience 10000))
	?fact1 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(>= ?f1 0)&:(< ?f1 1)))
	?fact2 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(>= ?f2 0)&:(< ?f2 1)))
	(test (neq ?fact1 ?fact2))
	=>
	(retract ?fact1)
	(bind ?f3 (+ ?f1 (* ?f2 (- 1 ?f1))))
	(modify ?fact2 (factor ?f3))) 

(defrule acumula-negativos-multivaluados
	(declare (salience 10000))
	?fact1 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(<= ?f1 0)&:(> ?f1 1)))
	?fact2 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(<= ?f2 0)&:(> ?f2 1)))
	(test (neq ?fact1 ?fact2))
	=>
	(retract ?fact1)
	(bind ?f3 (+ ?f1 (* ?f2 (+ 1 ?f1))))
	(modify ?fact2 (factor ?f3))) 
	
(defrule acumula-positivoYnegativo-multivaluados
	(declare (salience 10000))
	?fact1 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f1&:(>= ?f1 0)&:(< ?f1 1)))
	?fact2 <- (oavc-m (objeto ?o)
		(atributo ?a)
		(valor ?v)
		(factor ?f2&:(<= ?f2 0)&:(> ?f2 1)))
	(test (neq ?fact1 ?fact2))
	=>
	(retract ?fact1)
	(bind ?f3 (/ (+ ?f1 ?f2) (- 1 (min (abs ?f1) (abs ?f2)))))
	(modify ?fact2 (factor ?f3))) 
	

(defrule pseudomonas
	(oavc-u (objeto organismo)
		(atributo tincion) (valor gramneg) (factor ?f1))
	(oavc-u (objeto organismo)
		(atributo morfologia) (valor bacilos) (factor ?f2))
	(oavc-m (objeto paciente)
		(atributo es-un) (valor paciente-inmunocomprometido)
		(factor ?f3))
	(test (> (min ?f1 ?f2 ?f3) 0.2))
=>
	(bind ?certezaRegla 0.6)
	(bind ?f (* (min ?f1 ?f2 ?f3) ?certezaRegla))
	(assert (oavc-u (objeto organismo)
		(atributo identidad) (valor pseudomonas) (factor ?f)))
) 

(defrule paciente-inmunocomprometido-1 "AMBAS"
	(oavc-u (objeto paciente)
		(atributo grupo-edad) (valor tercera-edad) (factor ?f1&:(> ?f1 0.2)))
	(oavc-u (objeto paciente)
		(atributo operacion-reciente) (valor si) (factor ?f2&:(> ?f2 0.2)))
=>
	(bind ?f (* (max ?f1 ?f2) 1.0)) ;max porque modelamos OR
	(assert (oavc-m (objeto paciente) (atributo es-un)
	(valor paciente-inmunocomprometido) (factor ?f)))
)

(defrule paciente-inmunocomprometido-2 "SOLO primera"
	(oavc-u (objeto paciente)
		(atributo grupo-edad) (valor tercera-edad) (factor ?f1&:(> ?f1 0.2)))
	(not (oavc-u (objeto paciente)
		(atributo operacion-reciente) (valor si) (factor ?f2&:(> ?f2 0.2))))
=>
	(bind ?f (* ?f1 1.0))
	(assert (oavc-m (objeto paciente) (atributo es-un)
	(valor paciente-inmunocomprometido) (factor ?f)))
)

(defrule paciente-inmunocomprometido-3 "SOLO segunda"
	(not (oavc-u (objeto paciente)
		(atributo grupo-edad) (valor tercera-edad) (factor ?f1&:(> ?f1 0.2))))
	(oavc-u (objeto paciente)
		(atributo operacion-reciente) (valor si) (factor ?f2&:(> ?f2 0.2)))
=>
	(bind ?f (* ?f2 1.0))
	(assert (oavc-m (objeto paciente) (atributo es-un)
	(valor paciente-inmunocomprometido) (factor ?f)))
)

(deffacts inmunocomprometido "Evaluar OR"
(oavc-u (objeto paciente)
	(atributo grupo-edad)
	(valor tercera-edad)
	(factor 0.1))
(oavc-u (objeto paciente)
	(atributo operacion-reciente)
	(valor si)
	(factor 0.8)))
