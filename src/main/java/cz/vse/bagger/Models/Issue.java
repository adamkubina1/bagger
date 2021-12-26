package cz.vse.bagger.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Issue {
    private IntegerProperty Id_Issue;
    private IntegerProperty Id_Project;
    private IntegerProperty Id_Creater;
    private IntegerProperty Id_Closer;
    private StringProperty Issue_Title;
    private StringProperty Issue_Description;
    private SimpleObjectProperty<Date> Start_Date;
    private SimpleObjectProperty<Date> End_Date;
    private IntegerProperty Importance;
}
