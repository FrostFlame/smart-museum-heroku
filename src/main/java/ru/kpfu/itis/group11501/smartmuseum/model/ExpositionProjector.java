package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;

@Entity
@Table(name = "expositions_projectors")
public class ExpositionProjector {
    public ExpositionProjector() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Projector.class)
    @JoinColumn(name = "projectorsid", referencedColumnName = "id")
    private Projector projector;

    @ManyToOne(targetEntity = Exposition.class)
    @JoinColumn(name = "expositionsid", referencedColumnName = "id")
    private Exposition exposition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }
}
