package org.iesch.proyectobase.proyectobase.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MiRespuesta {
    private List<Fact> factList=new ArrayList<>();
    public void addone(Fact fact){
        factList.add(fact);
    }
}
