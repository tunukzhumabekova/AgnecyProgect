package peaksoft.entity;

import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.HouseType;


@Entity
@Table(name = "houses")
@Getter
@Setter
@NoArgsConstructor
public class House {
     @Id
     @GeneratedValue(
             generator = "house_gen",
             strategy = GenerationType.SEQUENCE)
     @SequenceGenerator(
             name = "house_gen",
             sequenceName = "house_seq",
             allocationSize = 1)
     private Long id;
     @NotNull
     @Column(name = "house_type")
     private HouseType houseType;
     @NotNull
     private String address;
     @NotNull
     private int price;
     @NotNull
     private int room;
     @NotNull
     private String country;
     @NotNull
     private String description;
     @NotNull
     @Column(name="image_link")
     private String imageLink;
     @NotNull
     @Column(name = "is_booked")
     private boolean isBooked;

     @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
     private Agency agency;
     @OneToOne(mappedBy = "house",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
     private Booking booking;

}
