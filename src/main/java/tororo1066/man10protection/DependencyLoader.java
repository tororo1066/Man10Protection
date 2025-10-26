package tororo1066.man10protection;

import tororo1066.tororopluginapi.AbstractDependencyLoader;
import tororo1066.tororopluginapi.Library;
import tororo1066.tororopluginapi.LibraryType;

public class DependencyLoader extends AbstractDependencyLoader {

    public DependencyLoader() {}

    @Override
    public Library[] getDependencies() {
        return new Library[]{
                LibraryType.KOTLIN.createLibrary("2.2.0"),
        };
    }
}
