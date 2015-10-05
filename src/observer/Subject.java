package observer;

/**
 * Padrao de projeto Observer
 * http://www.devmedia.com.br/padrao-de-projeto-observer-em-java/26163
 * @author Eduardo Scartezini
 *
 */
public interface Subject {

		public void attachObserver(Observer observer);
		public void detachObserver(Observer observer);
		public void notifyObserver();
}
