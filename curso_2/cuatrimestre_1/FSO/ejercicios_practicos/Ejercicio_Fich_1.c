#include<stdio.h>
#include <stdlib.h>


int main (int argc, char **argv)

{

FILE *fpin, *fpout_fich, *fpout_std;

float dat;

int cont=0;



if (argc < 3)

 {

 fprintf (stderr,"ERROR numero de argumentos insuficiente\n");

 exit (1);

 }



if ( (fpin = fopen(argv[1], "r")) == NULL)

  {

  fprintf (stderr,"ERROR No se pudo abrir %s\n", argv[1]);

  exit (2);

  }

if ( (fpout_fich = fopen(argv[2], "wb")) == NULL)

  {

  fprintf (stderr,"ERROR No se pudo abrir %s\n", argv[2]);

  exit (2);

  }

fpout_std = stdout;





while (fscanf (fpin,"%f",&dat) != -1)

 {

 if ( fwrite(&dat, sizeof(float), 1, fpout_fich) != 1)

   {

   fprintf(stderr, "ERROR al escribir dato %d en %s\n", cont, argv[2]);

   exit(3);

   }

 fprintf (fpout_std,"Dato escrito %.2f\n",dat); // se muestra con 2 decimales


 cont++;

}



fclose (fpin);

fclose (fpout_fich);

if ( (fpout_fich = fopen(argv[2], "rb")) == NULL)

  {

  fprintf (stderr,"ERROR No se pudo abrir %s\n", argv[2]);

  exit (2);

  }

while(fread(&dat,sizeof(float), 1, fpout_fich) > 0){
  fprintf (fpout_std,"Dato leido %.2f\n",dat); // se muestra con 2 decimales
}
return 0;

}
