package designpattern.visitorpattern;

public interface Visitable {
    /**
     *
     * @param v desired implementation of visitor
     * @param a the parameter with which visitor is called
     * @return visitor result
     */
    boolean accept(Visitor v, String a);
}
