/**
 * Copyright (c) 2016, Khudnitsky. All rights reserved.
 */
package by.htp.jd2.belastotskaya.Hotel.entities;

import java.io.Serializable;

/**
 * Describes the abstract entity <b>Entity</b>
 * @author khudnitsky
 * @version 1.0
 *
 */
public abstract class Entity implements Serializable{
    private static final long serialVersionUID = 1L;
    protected int id;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity [id=" + id + "]";
    }

    /**
     * Creates new entity </b>
     */
    public Entity() {}

    /**
     * Creates new entity </b>
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
}
