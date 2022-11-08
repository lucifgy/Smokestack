import arithmetic_e.arithmeticE;
import customException.wrongInt;
import fileNotFound.filenotFound;
import maxValue.maxInt;
import noClassFound.noClass;
import noMethod.noMethod;
import nullPointer.nullPointer;
import numberFormat.numberFormat;
import out_of_bounds.outofBounds;

public class Main {
    public static void main(String[] args) {
        outofBounds outofBounds = new outofBounds();
        outofBounds.wBug();
        outofBounds.corrected();

        arithmeticE arithmeticE = new arithmeticE();
        arithmeticE.wBug();
        arithmeticE.corrected();

        filenotFound fnf = new filenotFound("E://file.txt");
        fnf.wBug();
        //Doesn't have corrected func, bug depends on input:
        // /Users/syphilis/Smokestack/1_2/Block2/Block2.2/src/Main.java

        nullPointer np = new nullPointer();
        np.wBug();
        np.corrected();

        noClass nc = new noClass();
        nc.wBug("java.lang.Integer");
        nc.wBug("java.lang.Inte");
         //no corrected method, result depends on input

        numberFormat nf = new numberFormat();
        nf.wBug("12a");
        nf.wBug("123");
        //no corrected, bug depends on input.

        noMethod nm = new noMethod();
        nm.wBug();
        nm.corrected();

        wrongInt wi = new wrongInt();
        wi.wBug(5);
        wi.wBug(6);
        //Depends on input...

        maxInt maxInt = new maxInt();
        maxInt.wBug();
        //Doesn't have corrected func, bug depends on input.
    }
}