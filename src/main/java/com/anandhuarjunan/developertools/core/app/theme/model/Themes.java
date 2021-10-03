package com.anandhuarjunan.developertools.core.app.theme.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  

public class Themes
{
    private Theme[] theme;

    @XmlElement  

    public Theme[] getTheme ()
    {
        return theme;
    }

    public void setTheme (Theme[] theme)
    {
        this.theme = theme;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [theme = "+theme+"]";
    }
}