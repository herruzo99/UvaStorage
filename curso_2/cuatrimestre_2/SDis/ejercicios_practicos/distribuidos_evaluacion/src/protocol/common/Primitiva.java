package protocol.common;

public enum Primitiva {
  HELLO,          /* nombre usuario/respuesta */
  PUSH,           /* id-cola + mensaje */
  PUSH_OK,        /* (ack de push) */
  PULL_WAIT,      /* id-cola */
  PULL_NOWAIT,    /* id-cola */
  PULL_OK,        /* mensaje (resp. pull)*/
  NOTHING,        /* (cola vacía, resp. pull_nowait) */
  DENIED,		  /*mensaje(resp. push en una cola inexsitente por un cliente sin permiso*/
  NOTUNDERSTAND;  /* (error protocolo) */
}
