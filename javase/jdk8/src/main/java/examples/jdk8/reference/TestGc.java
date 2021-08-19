package examples.jdk8.reference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**-verbose:gc -Xms80m -Xmx80m**/
public class TestGc {

	public static void main(String[] args) {

		List<M> l = new ArrayList<M>();
		for (int i = 1; i < 1000000; i++) {
			l.add(new M());
		}

		/*
		 * List<java.lang.ref.SoftReference<M>> lm = new
		 * ArrayList<java.lang.ref.SoftReference<M>>(); for(int i = 1 ;i<100000;i++) {
		 * lm.add(new java.lang.ref.SoftReference<M>(new M())); }
		 */

		int i = 0;
		for (Iterator iterator = l.iterator(); iterator.hasNext();) {
			i++;
			M m = (M) iterator.next();
			System.out.println(m.data);
			iterator.remove();
			/*
			 * if (i % 1000 == 0) { System.gc(); }
			 */
		}
		l = null ;

		/*
		 * int i = 0; for (M m : l) { i++; System.out.println(m.data); m = null; if (i %
		 * 1000 == 0) { System.gc(); } }
		 */

		/*
		 * for(int i=0;i<l.size();i++) { System.out.println(l.get(i).data); M m =
		 * l.get(i) ; m = null ; }
		 */

	}

	static class M {
		public Long data = 4L;

		@Override
		protected void finalize() throws Throwable {
			System.out.print(this.hashCode() + "被回收");
		}

	}
}
