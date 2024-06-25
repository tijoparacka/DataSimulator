
package com.tijo.examples;

import com.tijo.streaming.impl.domain.generic.GenericEvent;
import org.apache.commons.lang.builder.ToStringBuilder;


public class Dummy10 extends GenericEvent {

    private String time ;
    private String 	col1 	;
    private String 	col2 	;
    private String 	col3	;
    private String 	col4	;
    private String 	col5	;
    private String 	col6	;
    private String 	col7	;
    private String 	col8	;
    private String 	col9	;
    private String 	col10	;

    public String getTime()
    {
        return time;
    }

    public String getCol1()
    {
        return col1;
    }

    public String getCol2()
    {
        return col2;
    }

    public String getCol3()
    {
        return col3;
    }

    public String getCol4()
    {
        return col4;
    }

    public String getCol5()
    {
        return col5;
    }

    public String getCol6()
    {
        return col6;
    }

    public String getCol7()
    {
        return col7;
    }

    public String getCol8()
    {
        return col8;
    }

    public String getCol9()
    {
        return col9;
    }

    public String getCol10()
    {
        return col10;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public void setCol1(String col1)
    {
        this.col1 = col1;
    }

    public void setCol2(String col2)
    {
        this.col2 = col2;
    }

    public void setCol3(String col3)
    {
        this.col3 = col3;
    }

    public void setCol4(String col4)
    {
        this.col4 = col4;
    }

    public void setCol5(String col5)
    {
        this.col5 = col5;
    }

    public void setCol6(String col6)
    {
        this.col6 = col6;
    }

    public void setCol7(String col7)
    {
        this.col7 = col7;
    }

    public void setCol8(String col8)
    {
        this.col8 = col8;
    }

    public void setCol9(String col9)
    {
        this.col9 = col9;
    }

    public void setCol10(String col10)
    {
        this.col10 = col10;
    }



    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}
