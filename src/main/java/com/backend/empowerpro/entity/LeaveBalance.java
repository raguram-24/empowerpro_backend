package com.backend.empowerpro.entity;
import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "leave_balance")
public class LeaveBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "balance_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "emp_id", nullable = false)
    private Employee employee;

    @Column(name = "total_available_leaves", nullable = false)
    private int totalAvailableLeaves = 30; // Default value

    @Column(name = "approved_leaves", nullable = false)
    private int approvedLeaves = 0;

    @Column(name = "rejected_leaves", nullable = false)
    private int rejectedLeaves = 0;

    /**
     * Method to deduct available leaves.
     */
    public void deductAvailableLeaves(int days) {
        this.totalAvailableLeaves -= days;
        this.approvedLeaves += days;
    }

    /**
     * Method to increment rejected leaves.
     */
    public void incrementRejectedLeaves() {
        this.rejectedLeaves++;
    }
}
