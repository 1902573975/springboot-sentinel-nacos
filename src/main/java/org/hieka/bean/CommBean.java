package org.hieka.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommBean implements Serializable {

    private Date birthday;
}
