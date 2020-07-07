# -*-coding=utf-8-*-
from time import time
import pygtk
import gtk
import gobject
import copy
from Buscaminas import Tablero


# clase de glade
class Princi:

    def __init__(self):

        # llamda a gtk y al archivo glade donde tenemos toda la interfaz
        self.glade = gtk.Builder()
        self.glade.add_from_file('Pantalla_minas.glade')

        # ventanas principales emergentes
        self.ventana_princi = self.glade.get_object('ventana_princi')
        self.ventana_fichero = self.glade.get_object('ventana_fichero')

        # matrices para ajustar los tamaños y formas del menú principal y las celdas del buscaminas
        self.ajuste_menu = self.glade.get_object('ajuste_menu')
        self.matriz_minas = self.glade.get_object('matriz_minas')

        # matriz donde se colocan las celdas del buscaminas
        self.pantalla_juego = self.glade.get_object('pantalla_juego')

        # conjunto de etiquetas que se muestran en las pantallas
        self.etiqueta_mina = self.glade.get_object('etiqueta_mina')
        self.etiqueta_tiempo = self.glade.get_object('etiqueta_tiempo')
        self.etiqueta_error = self.glade.get_object('etiqueta_error')
        self.etiqueta_error_juego = self.glade.get_object('etiqueta_error_juego')

        # menús que se muestran en la parte derecha de la venta, los cuales cambian según ganes o pierdas
        self.menu_lateral = self.glade.get_object('menu_lateral')
        self.submenu_etiquetas = self.glade.get_object('submenu_etiquetas')
        self.submenu_ganado = self.glade.get_object('submenu_ganado')
        self.submenu_perdido = self.glade.get_object('submenu_perdido')

        # botones del menú lateral principal
        self.boton_pausa = self.glade.get_object('boton_pausa')
        self.boton_empezar = self.glade.get_object('boton_empezar')
        self.boton_dificultad = self.glade.get_object('boton_dificultad')

        # llamada para conectar todos los botnes con sus correspondientes funciones
        self.glade.connect_signals(self)
        # icono y título del juego
        self.ventana_princi.set_icon_from_file("iconos/icono.ico")
        self.ventana_princi.set_title("Buscaminas")

        # inicializaciones y llamada al proceso cargar_imagenes
        self.matriz = []
        self.imgs = []
        self.cargar_imagenes()
        self.primer_click = True
        self.selec = 0
        self.tiempo_final = gtk.Label()
        self.juego = Tablero()
        self.jugando = False
        self.guardar_juego = self.juego
        self.interfaz_tablero = gtk.Fixed()
        self.pausado = False
        self.texto_pausado = gtk.Label()
        self.tiempo0 = time()
        self.ventana_princi.show()

    # proceso que cargar las imagenes en la lista imgs anteriormente inicializada
    def cargar_imagenes(self):
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/abierta.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/1.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/2.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/3.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/4.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/5.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/6.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/?.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/marcada.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/marcada_mal.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/bum.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/cerrada.png"))
        self.imgs.append(gtk.gdk.pixbuf_new_from_file("hexagonos/mina.png"))

    # proceso que el contador de tiempo cuando el usuario hace le primer click, lo detiene cuando el juego termina o el
    # usuario pausa la jugada y lo reaunuda si despausa la jugada
    def reloj(self):
        if self.jugando and not self.primer_click:
            tiempo_act = int(time() - self.tiempo0)
            self.etiqueta_tiempo.set_label("{0:02}:{1:02}".format(tiempo_act / 60, tiempo_act % 60))
            gobject.timeout_add(100, self.reloj)
        else:
            if not self.pausado:
                self.etiqueta_tiempo.set_label("00:00")

    # proceso que cierra y detiene el programa
    def on_window1_delete_event(self, widget):
        # cierra el programa
        gtk.main_quit()

    # proceso que cierra la venta el fichero
    def cerrar_fichero(self, widget):
        self.ventana_fichero.hide()

    # proceso vinculado a los botones del menú principal (1º menú mostrado)
    def botones_menu(self, widget):

        # dependiendo de la dificultad seleccionada se manda la información a juego y se crea la matriz interna
        if widget.get_label() == 'facil':
            self.juego = Tablero(10, 9, 9)
            self.juego.crear_matriz()
            self.selec = 1
            # se oculta la ventana del menú y se llama al proceso que crea la matriz gráfica para que el usuario juegue
            self.ventana_princi.remove(self.ajuste_menu)
            self.crear_interfaz_tablero()

        elif widget.get_label() == 'medio':
            self.juego = Tablero(40, 16, 16)
            self.juego.crear_matriz()
            self.selec = 2
            self.ventana_princi.remove(self.ajuste_menu)
            self.crear_interfaz_tablero()
        elif widget.get_label() == 'experto' \
                                   '':
            self.juego = Tablero(99, 16, 30)
            self.juego.crear_matriz()
            self.selec = 3
            self.ventana_princi.remove(self.ajuste_menu)
            self.crear_interfaz_tablero()

        # aquí han escogido leer fichero y se llama a ese mismo procedimiento
        else:
            self.selec = 4
            self.leer_tablero()



    # proceso vinculado al botón de empezar de nuevo
    def empezar_nuevo(self, widget):

        # se borra la matriz interna y
        self.matriz = []
        self.matriz_minas.remove(self.interfaz_tablero)
        self.interfaz_tablero.destroy()
        self.ventana_princi.remove(self.pantalla_juego)

        # si el usuario había escogido entre facil, medio y difil se vuelve a crear un tablero aleatorio
        if self.selec < 4:
            if self.selec == 1:
                self.juego = Tablero(10, 9, 9)
            elif self.selec == 2:
                self.juego = Tablero(40, 16, 16)
            elif self.selec == 3:
                self.juego = Tablero(99, 16, 30)

            self.juego.crear_matriz()

        # si el usuario había escogido fichero se reinicia la partida, con las porpiedades indicadas en el
        # fichero, anteriormente guardada, es decir sin reeler el fichero.
        else:
            self.juego = copy.deepcopy(self.guardar_juego)
        self.crear_interfaz_tablero()

    # función que abre la ventana para elegir fichero
    def elegirFichero(self):
        dlg = gtk.FileChooserDialog(
            "Abrir fichero", None,
            gtk.FILE_CHOOSER_ACTION_OPEN,
            (gtk.STOCK_CANCEL, gtk.RESPONSE_CANCEL,
             gtk.STOCK_OPEN, gtk.RESPONSE_OK))

        if dlg.run() == gtk.RESPONSE_OK:
            res = dlg.get_filename()
        else:
            res = None
        dlg.destroy()
        return res

    # esta función lee el el fichero indicado por el usuario
    def leer_tablero(self):
        try:
            nombre = self.elegirFichero()
            fichero = open(nombre, 'r')
            f = fichero.readline()
            fil, col = (map(int, f.split()))
            matriz = []
            mina = 0
            self.juego = Tablero()
            # bucle para rellenar la matriz con los datos del fichero
            # cuenta el numero de minas que hay
            for i in range(0, fil):
                fila = []
                f = fichero.readline()
                for j in range(0, col):
                    fila.append(f[j])
                    if f[j] == "*":
                        mina += 1
                matriz.append(fila)
            self.juego.leer_matriz(matriz)
            print matriz
            # guarda una copia de la matriz que acaba de creear por si el usuario presiona empezar de nuevo
            self.guardar_juego = copy.deepcopy(self.juego)
            self.ventana_princi.remove(self.ajuste_menu)
            self.crear_interfaz_tablero()

        except:
            # si no existe el ficho o no es una extensión válida muestra este mensaje por pantalla
            self.etiqueta_error.set_label("No se ha podido abrir el fichero " + nombre.split("/")[-1])
            self.ventana_fichero.show()

    # función que crea la interfaz del tablero
    def crear_interfaz_tablero(self):
        # esconde la ventana del fichero por si ha sido necesaria abrirla y limpia la etiqueta del error de esta
        # ventana por lo mismo
        self.ventana_fichero.hide()
        self.etiqueta_error.set_label("")

        # esoconde los menús de ganado y perdido y muestra el menú de las minas y el tiempo
        self.submenu_ganado.hide()
        self.submenu_perdido.hide()
        self.submenu_etiquetas.show()
        # carga las imágenes guardadas
        self.interfaz_tablero = gtk.Fixed()

        fila = []
        # en este bucle va cargando, colocando y activando la señal de clicked de las celdas del buscaminas
        for i in range(self.juego.filas):
            for j in range(self.juego.columnas):
                evnt = gtk.EventBox()
                evnt.show()
                img = gtk.Image()
                img.set_from_pixbuf(self.imgs[11])
                evnt.set_visible_window(False)
                evnt.add(img)
                img.show()
                evnt.connect('button-press-event', self.boton_mina)
                fila.append(evnt)
                # comprueba si es par o impar para colocarlas de diferente forma
                if i % 2 == 0:
                    self.interfaz_tablero.put(evnt, 18 + 35 * j, 36 * i)
                else:
                    self.interfaz_tablero.put(evnt, 35 * j, 36 * i)
            self.matriz.append(fila)
            fila = []
        # coloca toda la matriz creada en el medio de la ventana y la muestra
        self.matriz_minas.attach(self.interfaz_tablero, 1, 3, 1, 3)
        self.interfaz_tablero.show()
        # muestra la etiqueta que indica las minas que se han marcado
        self.etiqueta_mina.set_label(str(self.juego.minas - self.juego.minas_restantes) + '/' + str(self.juego.minas))
        self.etiqueta_mina.show()

        # conjunto de inicializaciones
        self.primer_click = True
        self.tiempo_final.destroy()
        self.etiqueta_error_juego.set_label("")
        self.ventana_princi.resize(1, 1)
        self.pausado = False
        self.texto_pausado = gtk.Label()
        self.texto_pausado.set_usize(18 + 35 * self.juego.columnas, 36 * self.juego.filas)
        self.texto_pausado.set_label("PAUSA!")
        self.texto_pausado.show()
        self.boton_pausa.set_sensitive(False)

        # el conjunto de lo mostrado anteriormente se encuentra en "pantalla_juego", esta acción hace que esta pantalla
        # se muestre sobre la ventana prinicipal
        self.ventana_princi.add(self.pantalla_juego)

    # proceso vicnulado al boton de cambiar dificultad
    def dificultad(self, widget):
        # elimina la matriz interna y la que ve el usuario y vuelve a mostrar el menú principal
        self.matriz = []
        self.interfaz_tablero.destroy()
        self.ventana_princi.remove(self.pantalla_juego)
        self.ventana_princi.add(self.ajuste_menu)

    # proceso vicnulado al boton de cambiar dificultad
    def pausa(self, widget):

        # si el usuaria presiona el botón y el juego estaba pausado esconde el texto de pausa y muestra lo neecsario para
        # seguir juando
        if self.pausado:
            self.matriz_minas.remove(self.texto_pausado)
            self.matriz_minas.attach(self.interfaz_tablero, 1, 3, 1, 3)
            self.jugando = True
            self.pausado = False
            self.etiqueta_error_juego.show()
            gobject.timeout_add(100, self.reloj)
            self.boton_dificultad.set_sensitive(True)
            self.boton_empezar.set_sensitive(True)
        # si el usuario quiere pausar el juego, la, matriz de minas se esconde, se muestra el el texto de pausa y se
        # para el tiempo
        else:
            self.matriz_minas.remove(self.interfaz_tablero)
            self.matriz_minas.attach(self.texto_pausado, 1, 3, 1, 3)
            self.jugando = False
            self.pausado = True
            self.etiqueta_error_juego.hide()
            self.boton_dificultad.set_sensitive(False)
            self.boton_empezar.set_sensitive(False)

    # Sirve para redimensionar el menú principal del juego
    def on_window1_check_resize(self, ventana):
        # x e y son el tamaño de la ventana y dif su diferencia
        # para que los botones siempre sean cuadrados perfectos se añade espacio en la tabla exterior
        x, y = ventana.get_size()
        dif = abs(x - y)
        dif = dif / 2
        if x >= y:
            self.ajuste_menu.set_col_spacing(0, dif)
            self.ajuste_menu.set_col_spacing(1, dif)
            self.ajuste_menu.set_row_spacing(0, 0)
            self.ajuste_menu.set_row_spacing(1, 0)
        else:
            self.ajuste_menu.set_row_spacing(0, dif)
            self.ajuste_menu.set_row_spacing(1, dif)
            self.ajuste_menu.set_col_spacing(0, 0)
            self.ajuste_menu.set_col_spacing(1, 0)

    # Funcion llamada por todas las celdas del tablero
    def boton_mina(self, widget, evento):
        # Si es llamada significa que se empieza a jugar o que ya se está jugado
        self.jugando = True

        # En el primer click se activa el boton de pausado y comienza el tiempo
        if self.primer_click:
            self.boton_pausa.set_sensitive(True)
            self.tiempo0 = time()
            gobject.timeout_add(1000, self.reloj)

        # Se busca que boton a sido pulsado comprarandolo con todos los botones
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz[0])):
                if self.matriz[i][j] == widget:

                    # Si se ha hecho click izquierdo
                    if evento.button == 1:

                        # Se valida la jugada si es incorrecta se escribe el error en pantalla
                        if self.juego.validar_jugada(i, j, 1) == True:
                            self.etiqueta_error_juego.set_label("")

                            # Se abre la celda con las mismas coordenadas que el boton
                            if self.primer_click:

                                # Si es la primera vez no se permita explotar
                                self.juego.abrir_celda(i, j, -1)
                            else:
                                self.juego.abrir_celda(i, j, True)
                        else:
                            self.etiqueta_error_juego.set_label(self.juego.validar_jugada(i, j, 1))

                    # Si se ha hecho click derecho
                    elif evento.button == 3:
                        # Se valida la jugada si es incorrecta se escribe el error en pantalla
                        if self.juego.validar_jugada(i, j, 0) == True:
                            self.etiqueta_error_juego.set_label("")
                            self.juego.marcar_celda(i, j)
                        else:
                            self.etiqueta_error_juego.set_label(self.juego.validar_jugada(i, j, 0))

        # Una vez hecha la jugada se actualizan las imagenes con otras nuevas
        for i in range(len(self.juego.matriz)):
            for j in range(len(self.juego.matriz[0])):
                img = gtk.Image()
                if self.juego.matriz[i][j].marcada:  # celda marcada
                    img.set_from_pixbuf(self.imgs[8])
                elif not self.juego.matriz[i][j].abierta:  # celda cerrada
                    img.set_from_pixbuf(self.imgs[11])
                elif not self.juego.matriz[i][j].mina:  # celda abierta
                    num = self.juego.matriz[i][j].minas_vecinas
                    if num < 0:
                        img.set_from_pixbuf(self.imgs[7])  # celda abierta con demasiadas marcas alrededor
                    else:
                        img.set_from_pixbuf(self.imgs[num])

                # Al estar las imagenes dentro de una event box se la llama con get_children y se borra
                self.matriz[i][j].remove(self.matriz[i][j].get_children()[0])
                img.show()
                # Se añade la nueva imagen
                self.matriz[i][j].add(img)

        # Si se gana o se pierda de deja de jugar y no se permite dar al boton de pausa
        # Se comprueba si se a perdido
        if self.juego.minas_restantes == -1:
            self.jugando = False
            self.boton_pausa.set_sensitive(False)
            self.fin_perdido()

        # Se comprueba si se a ganado
        if self.juego.fin:
            self.jugando = False
            self.boton_pausa.set_sensitive(False)
            self.fin_ganado()

        # Una vez acabado ya no es el primer turno
        self.primer_click = False

        # Se actualiza la label de minas restantes
        self.etiqueta_mina.set_label(
            str(self.juego.minas - self.juego.minas_restantes) + '/' + str(self.juego.minas))

    # Funcion que ejecuta el perder la partida
    def fin_perdido(self):

        # Se muestran todas las minas y se añaden las banderas mal puestas
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz[0])):
                img = gtk.Image()
                if self.juego.matriz[i][j].mina:
                    if self.juego.matriz[i][j].abierta:
                        img.set_from_pixbuf(self.imgs[10])
                        self.matriz[i][j].remove(self.matriz[i][j].get_children()[0])
                        self.matriz[i][j].add(img)

                    elif not self.juego.matriz[i][j].marcada:
                        img.set_from_pixbuf(self.imgs[12])
                        self.matriz[i][j].remove(self.matriz[i][j].get_children()[0])
                        self.matriz[i][j].add(img)

                else:
                    if self.juego.matriz[i][j].marcada:
                        img.set_from_pixbuf(self.imgs[9])
                        self.matriz[i][j].remove(self.matriz[i][j].get_children()[0])
                        self.matriz[i][j].add(img)
                img.show()
                # Se desconecta la celda para que no pueda ser pulsada
                self.matriz[i][j].disconnect_by_func(self.boton_mina)
        self.matriz = []

        # Se guarda el tiempo total transcurrido y se muestra el menú de derota
        tiempo_act = int(time() - self.tiempo0)
        self.tiempo_final = gtk.Label()
        self.tiempo_final.set_label("Tiempo final: {0:02}:{1:02}".format(tiempo_act / 60, tiempo_act % 60))
        self.tiempo_final.show()
        self.submenu_etiquetas.hide()
        self.submenu_perdido.add(self.tiempo_final)

        self.submenu_perdido.show()

    # Funcion que ejecuta el ganar la partida
    def fin_ganado(self):

        # Se desconectan todas las celdas para que no puedan ser dadas
        for i in range(len(self.matriz)):
            for j in range(len(self.matriz[0])):
                self.matriz[i][j].disconnect_by_func(self.boton_mina)
        self.matriz = []

        # Se guarda el tiempo total transcurrido y se muestra el menú de victoria
        tiempo_act = int(time() - self.tiempo0)
        self.tiempo_final = gtk.Label()
        self.tiempo_final.set_label("Tiempo final: {0:02}:{1:02}".format(tiempo_act / 60, tiempo_act % 60))
        self.tiempo_final.show()
        self.submenu_etiquetas.hide()
        self.submenu_ganado.add(self.tiempo_final)
        self.submenu_ganado.show()


if __name__ == '__main__':
    app = Princi()
    # inicio del bucle de programa
    gtk.main()
