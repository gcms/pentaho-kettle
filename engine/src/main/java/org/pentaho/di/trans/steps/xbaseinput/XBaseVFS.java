package org.pentaho.di.trans.steps.xbaseinput;

import br.gov.go.saude.dbc.DBCInputStream;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.pentaho.di.core.vfs.KettleVFS;

import java.io.InputStream;
import java.util.regex.Pattern;

public class XBaseVFS {
    public static InputStream getInputStream(FileObject file) throws FileSystemException {
        return getInputStream( file.getName().getBaseName(), KettleVFS.getInputStream(file) );
    }

    public static InputStream getInputStream(String fileName, InputStream inputStream) {
        return isDBCFile( fileName ) ? new DBCInputStream( inputStream ) : inputStream;
    }


    private static final Pattern DBC_FILE_PATTERN = Pattern.compile(".*\\.dbc$", Pattern.CASE_INSENSITIVE);

    private static boolean isDBCFile(String fileName) {
        return DBC_FILE_PATTERN.matcher( fileName ).matches();
    }
}
