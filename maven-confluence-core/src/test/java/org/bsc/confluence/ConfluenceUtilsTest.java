package org.bsc.confluence;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConfluenceUtilsTest {

    @Test
    public void decode() throws Exception {
        String input = "<coDe>[I][RE]'.*moon[^dab]+'</CODE> : <EM>all</Em> <u>implemented</U> <s>specifications</S> <DEL>having</del> <Strong>the</Strong> <i>RE<I> '.*moon[^dab]+'";
        String decoded = ConfluenceUtils.decode(input);
        assertThat(decoded, is("{{\\[I\\]\\[RE\\]'.\\*moon\\[^dab\\]\\+'}} : _all_ +implemented+ -specifications- -having- *the* _RE_ '.\\*moon\\[^dab\\]\\+'"));
    }

    @Test
    @Ignore
    public void pathTest() throws Exception {

        //File f = new File(".");

        Path pp = Paths.get(  "http://test.xml");

        Assert.assertThat( Files.exists(pp), IsEqual.equalTo(false) );

        Path p1 = Paths.get(  "classpath:test.xml");

        Assert.assertThat( Files.exists(p1), IsEqual.equalTo(false) );

    }

    @Test
    public void sanitationNotNeeded() {
        assertThat(ConfluenceUtils.sanitizeLabel("label"), is("label"));
    }

    @Test
    public void sanitation() {
        assertThat(ConfluenceUtils.sanitizeLabel("labe:l"), is("labe-l"));
    }

    @Test
    public void sanitationFull() {
        assertThat(ConfluenceUtils.sanitizeLabel(": ; , . , ? & [ ] ( ) # ^ * ! @"),
                is("-------------------------------"));
    }

}
