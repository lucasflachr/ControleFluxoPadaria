package br.com.rocha.Hibernate;

import java.sql.DriverManager;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.EJB3NamingStrategy;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

   private static final Configuration configuration;

   private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

   private static final ThreadLocal<Transaction> transaction = new ThreadLocal<Transaction>();

   private static final ThreadLocal<Integer> count = new ThreadLocal<Integer>();

   private static final ThreadLocal<Integer> countSession = new ThreadLocal<Integer>();

   private static final ThreadLocal<Boolean> rollback = new ThreadLocal<Boolean>();

   static {

       try {
           Class.forName("org.postgresql.Driver");

           try {
               DriverManager.registerDriver(new org.postgresql.Driver());
           } catch (Exception e) {
               e.printStackTrace();
           }

           configuration = new AnnotationConfiguration();
           configuration.setNamingStrategy(new EJB3NamingStrategy());

           sessionFactory = configuration.configure().buildSessionFactory();

       } catch (Exception ex) {
           throw new RuntimeException(
                   "[HibernateUtil] Problema de configura��o ou valida��o : "
                           + ex.getMessage(), ex);
       }
   }

   /**
    * Retorna sess�o do Hibernate. Se n�o houver sess�o, cria uma nova.
    *
    * @return Session Hibernate session
    * @throws Exception
    */
   public static Session currentSession() throws Exception {

       if (session.get() == null || !session.get().isOpen()) {
           session.set(sessionFactory.openSession());
           countSession.set(1);
       } else {
           countSession.set(countSession.get() + 1);
       }

       session.get().setFlushMode(FlushMode.COMMIT);

       // logger.debug("Recuperando - Numero de sessions:" +
       // countSession.get());

       return session.get();
   }

   /**
    * Fecha a sess�o do Hibernate.
    *
    * @return true se a sess�o foi fechada e false caso contr�rio - false
    *         indica que est� dentro de uma transa��o.
    * @throws Exception
    */
   public static boolean closeSession() throws Exception {
       if (transaction.get() != null) {
           return false;
       }

       try {
           if (session.get() != null) {
               try {
                   if (session.get().isOpen() && count.get() == null) {
                       session.get().flush();
                   }
               } finally {
                   if (session.get().isConnected()) {
                       session.get().disconnect();
                   }
                   session.get().close();
                   countSession.set(null);
               }
           }
       } finally {
           session.set(null);
       }

       return true;
   }

   /**
    * Inicia uma transa��o se for necess�rio. Caso j� exista uma, somente a
    * mant�m. Retorna a sess�o que est� nesta transa��o.
    *
    * @throws Exception
    *             Problemas ao obter conex�o ou iniciar transa��o.
    *
    */
   public static void currentTransaction() throws Exception {

       try {
           currentSession();
       } catch (Exception e) {
           throw new Exception("Erro ao obter sess�o do Hibernate: "
                   + e.getMessage(), e);
       }

       /*
        * se j� houver uma transa��o, somente incrementa nosso contador de
        * transa��es.
        */
       if (transaction.get() != null) {
           count.set(count.get() + 1);
           // logger.debug("Recuperando - Numero de transa��es:" +
           // count.get());
       } else {
           transaction.set(session.get().beginTransaction());
           count.set(1);
           rollback.set(false);
           // logger.debug("Abrindo - Numero de transa��es:" + count.get());
       }
   }

   /**
    * Somente tem efeito se a session tiver sido obtida atrav�s do m�todo
    * getManagedSession. Neste caso, executa um commit na transa��o associada
    * caso o total de chamadas de getManagedSession tenha sido igual ao total
    * de commits e caso n�o tenha sido chamado nenhum rollback. Caso algum
    * rollback tenha sido chamado, executa roolback e lan�a Exception.
    *
    * @throws Exception
    *
    */
   public static void commitTransaction() throws Exception {
       // logger.debug("Tentando commitar a transa��o");

       if (transaction.get() == null) {
           throw new Exception("N�o existe transa��o associada a esta sess�o!");
       }

       if (count.get() != null) {
           /*
            * caso n�o tenha sido executado um commit para cada begin, n�o
            * executa commit
            */
           try {
               if (count.get() - 1 > 0) {
                   session.get().flush();
                   count.set(count.get() - 1);
                   // logger
                   // .debug("commit - dentro de outra transacao - Transacoes
                   // Iniciadas - "
                   // + count.get());
                   return;
               }
               if (rollback.get()) {
                   transaction.get().rollback();
                   count.set(null);
                   transaction.set(null);
                   closeSession();
                   // logger.error(
                   // throw new Exception(
                   // "Voc� esperava um \"COMMIT\", mas no lugar um
                   // \"ROLLBACK\" foi executado. "
                   // + "Verifique a execu��o do seu c�digo. Provavelmente
                   // alguma transa��o aninhada executou um \"ROLLBACK\""
                   // + "mas n�o foi lan�ada excess�o para a classe que a
                   // chamou tratar e executar \"ROLLBACK\" tamb�m. Lembre-se:"
                   // + "Hibernate n�o suporta transa��es aninhadas. A classe
                   // HibernateUtil somente prov� uma maneira de sempre"
                   // + "utilizar a mesma transa��o.");
               }

               // Caso tente fazer commit e for GenericJDBCExcption deve
               // ser tratado pelo programador
               // logger.debug("commit - Transacoes Iniciadas - " +
               // count.get());

               transaction.get().commit();
               session.get().flush();
               count.set(null);
               transaction.set(null);

               closeSession();

               // if (count.get() == null) {
               // logger.debug("Fechando -Todas transa��es finalizadas!");
               // } else {
               // logger.debug("Fechando - Numero de transa��es: "
               // + count.get());
               // }
           } catch (Exception e) {
               e.printStackTrace();
               throw e;
           }
       } else {
           throw new Exception("N�o existe transa��o associada a esta sess�o!");
       }
   }

   /**
    * Somente tem efeito se a session tiver sido obtida atrav�s do m�todo
    * getManagedSession. Neste caso, executa um rollback na transa��o associada
    * caso o total de chamadas de getManagedSession tenha sido igual ao total
    * de commit/rollbacks.
    *
    * @throws Exception
    *
    */
   public static void rollbackTransaction() throws Exception {
       if (transaction.get() == null) {
           if (rollback.get()) {
               rollback.set(false);
               throw new Exception("\"ROLLBACK\" j� executado por um commit.");
           }
           throw new Exception("N�o existe transa��o associada a esta sess�o!");
       }

       if (count.get() != null) {
           count.set(count.get() - 1);

           // Foi chamado o rollback no meio da transa��o.

           if (count.get() > 0) {
               rollback.set(true);
               // logger
               // .debug("rollback - dentro de outra transacao - Transacoes
               // Iniciadas - "
               // + count.get());
               return;
           }

           transaction.get().rollback();
           transaction.set(null);
           rollback.set(false);
           closeSession();
           count.set(null);
           // logger.debug("rollback - Rollback efetuado");

       } else {
           throw new Exception("N�o existe transa��o associada a esta sess�o!");
       }
   }

   /**
    * Fecha a sess�o do Hibernate e todas as suas conex�es.
    *
    * @throws Exception
    */
   public static void shutdown() throws Exception {

       closeSession();

       session.set(null);
       transaction.set(null);
       count.set(null);
       sessionFactory.close();
       configuration.setInterceptor(null);
   }

   /**
    * Retorna o objeto Configuration usado
    *
    * @return Configuration
    */
   public static Configuration getConfiguration() {
       return configuration;
   }

   /**
    * Retorna a SessionFactory atual
    *
    * @return SessionFactory
    */
   public static SessionFactory getSessionFactory() {
       return sessionFactory;
   }

   /**
    * Configura um novo sessionFactory. Pode ser usado para alterar o arquivo
    * de configura��o do Hibernate.
    *
    * @param sessionFactory
    * @throws Exception
    */
   public static void setSessionFactory(SessionFactory sessionFactory)
           throws Exception {
       closeSession();
       HibernateUtil.sessionFactory = sessionFactory;
   }

   public static boolean isCurrentTransaction() {
       return transaction.get() != null;
   }

   public static boolean isCurrentSession() {
       return countSession.get() != null;
   }

   public static int getTransactionCount() {
       return count == null ? 0 : count.get() == null ? 0 : count.get();
   }
}
