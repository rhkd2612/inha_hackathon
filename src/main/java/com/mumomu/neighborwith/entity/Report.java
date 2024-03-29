package com.mumomu.neighborwith.entity;

import com.mumomu.neighborwith.common.SimpleDateFormatter;
import com.mumomu.neighborwith.entity.dto.ReportCreateForm;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Report {
    @Id
    @Column(name="report_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "user_id")
    private User sender;

    private String createTime;
    private String content;
    private String ownerTel;
    private boolean isAnonymous;

    public void setCreateTime(Date createTime) {
        this.createTime = SimpleDateFormatter.formatDateToString(createTime);
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public static Report newReport(ReportCreateForm reportCreateForm){
        return Report.builder()
                .content(reportCreateForm.getContent())
                .ownerTel(reportCreateForm.getOwnerTel())
                .isAnonymous(reportCreateForm.isAnonymous())
                .build();
    }
}
