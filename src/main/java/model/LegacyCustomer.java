package model;

import java.util.Objects;

public class LegacyCustomer {

    private String id;
    private String name;
    private String idType;
    private String idNumber;
    private String dob;

    public LegacyCustomer(String id, String name, String idType, String idNumber, String dob) {
        this.id = id;
        this.name = name;
        this.idType = idType;
        this.idNumber = idNumber;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getIdTypeIdNumber() {
        return this.idType + this.idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LegacyCustomer that = (LegacyCustomer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(idType, that.idType) && Objects.equals(idNumber, that.idNumber) && Objects.equals(dob, that.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, idType, idNumber, dob);
    }
}
