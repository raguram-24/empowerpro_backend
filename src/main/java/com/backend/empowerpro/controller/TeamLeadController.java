package com.backend.empowerpro.controller;

import com.backend.empowerpro.auth.service.AuthService;
import com.backend.empowerpro.dto.Blog.BlogCommentDto;
import com.backend.empowerpro.dto.Blog.BlogCreationDto;
import com.backend.empowerpro.dto.MarkCalendar.MarkCalendarDto;
import com.backend.empowerpro.dto.Project.ProjectCreationDto;
import com.backend.empowerpro.dto.Project.ProjectTaskCreationDto;
import com.backend.empowerpro.dto.attendance.SearchDateRangeDto;
import com.backend.empowerpro.dto.complaint.ComplaintDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.entity.*;
import com.backend.empowerpro.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v1/teamlead")
@RequiredArgsConstructor
public class TeamLeadController {
    private final AuthService authService;
    private final MarkCalendarService markCalendarService;
    private final BlogService blogService;
    private  final AddFavouriteService addFavouriteService;
    private  final BlogCommentService blogCommentService;
    private  final BlogViewService blogViewService;
    private final BlogRatingService blogRatingService;
    private  final AttendanceService attendanceService;
    private  final  EmployeeService employeeService;
    private  final ProjectTaskService projectTaskService;


//    -------------------------calendar marker------------------------------------------------

    @PostMapping("/markcalendar-create")
    public ResponseEntity<String> creation(@RequestBody MarkCalendarDto markCalendarDto){
        return ResponseEntity.ok(markCalendarService.createMarkCalendar(markCalendarDto));
    }

    @GetMapping("/getAllMarker/{userId}")
    public ResponseEntity<List<MarkCalendar>> getAllCalendarMarker(
            @PathVariable("userId") Long userId
    ) {
        List<MarkCalendar> Markers = markCalendarService.getAllCalendarMarker(userId);
        return ResponseEntity.ok(Markers);
    }

    @GetMapping("getOne/{id}")
    public ResponseEntity<MarkCalendar> getMarkerById(@PathVariable("id") Long MarkId) throws Throwable {
        MarkCalendar markCalendarDto=markCalendarService.getMarkerById(MarkId);
        return ResponseEntity.ok(markCalendarDto);
    }

    @DeleteMapping("/deleteMarker/{id}")
    public ResponseEntity<String> deleteMarker(@PathVariable("id") Long markId){
        markCalendarService.deleteMarker(markId);
        return ResponseEntity.ok("Marker deleted successfully!.");
    }

    @PutMapping("/update-state/{id}")
    public ResponseEntity<MarkCalendarDto> updateState(
            @PathVariable Long id,
            @RequestBody MarkCalendarDto markCalendarDto) {
        try {
            MarkCalendarDto updatedState = markCalendarService.updateState(id, markCalendarDto);
            return ResponseEntity.ok(updatedState);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    //-----------------------------------------------------Blog-------------------------------------------
    @PostMapping("/blog-create")
    public ResponseEntity<Blog> createBlog(@RequestBody BlogCreationDto blogCreationDto){
        System.out.println("hello");
        Blog createBlog=blogService.createBlog(blogCreationDto);
        return ResponseEntity.ok(createBlog);
    }


    @GetMapping("/all-blogs")
    public ResponseEntity<List<Blog>> getAllBlog(){
        List<Blog> AllBlog= blogService.getAllBlog();
        return  ResponseEntity.ok(AllBlog);
    }

    @GetMapping("getOneBlog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("id") Long BlogId) throws Throwable {
        Blog blogDto=blogService.getBlogById(BlogId);
        return ResponseEntity.ok(blogDto);
    }

    @DeleteMapping("/deleteBlog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable("id") Long BlogId) throws Throwable {
        blogService.deleteBlog(BlogId);
        return ResponseEntity.ok("Blog deleted successfully!.");
    }

    @GetMapping("/searchBlog")
    public ResponseEntity<List<Blog>> searchBlog(@RequestParam(required = false) String keyword) throws Exception {
        List<Blog> blogs =blogService.searchBlogName(keyword);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/getAllFavouriteBlog/{userId}")
    public ResponseEntity<List<Blog>> getAllFavouriteBlog(@PathVariable("userId")Long userId){
        List<Blog> blogs =blogService.getAllFavouriteBlog(userId);
        return ResponseEntity.ok(blogs);
    }

    //----------------------------------------------favourite blog--------------------------------------------
    @GetMapping("/AddFavourite-create/{userId}/{blogId}")
    public ResponseEntity<String> createAddFavourite(@PathVariable("userId") Long userId ,@PathVariable("blogId") Long blogId ) throws Throwable {
        Blog blog = blogService.getBlogById(blogId);
        return ResponseEntity.ok(addFavouriteService.createAddFavouriteBlog(userId,blog));
    }

    @DeleteMapping("/deleteAddFavourite/{id}")
    public ResponseEntity<String> deleteAddFavouriteBlog(@PathVariable("id") Long favouriteId){
        addFavouriteService.deleteAddFavouriteBlog(favouriteId);
        return ResponseEntity.ok("AddFavourite Blog deleted successfully!.");
    }


    @DeleteMapping("/deleteFavouriteBlog/{id}/{blogId}")
    public ResponseEntity<String> deleteFavouriteBlog(@PathVariable("id") Long userId,@PathVariable("blogId") Long blogId){
        addFavouriteService.deleteFavouriteBlog(userId,blogId);
        return ResponseEntity.ok("AddFavourite Blog deleted successfully!.");
    }
    @GetMapping("/getAllAddFavourite/{userid}")
    public  ResponseEntity<List<AddFavouriteBlog>> getAllAddFavouriteBlog(@PathVariable("userid") Long userId){
        List<AddFavouriteBlog> AllAddFavouriteBlog = addFavouriteService.getAllFavouriteBlogsByUserId(userId);
        return ResponseEntity.ok(AllAddFavouriteBlog);
    }

    @GetMapping("/getFavouriteBlog/{id}/{blogId}")
    public ResponseEntity<Long> getFavouriteBlog(@PathVariable("id")Long userId, @PathVariable("blogId")Long blogId) throws Throwable {
        Long addFavouriteBlog = addFavouriteService.getFavouriteBlog(userId,blogId);
        return  ResponseEntity.ok(addFavouriteBlog);
    }

//     ----------------------------------blog Comment--------------------------------------------------------

    @PostMapping("/createBlogComment")
    public ResponseEntity<BlogComment> createBlogComment(@RequestBody BlogCommentDto blogCommentDto){
        BlogComment createBlogComment = blogCommentService.createBlogComment(blogCommentDto);
        return ResponseEntity.ok(createBlogComment);
    }

    @GetMapping("/getBlogComment/{blogId}")
    public ResponseEntity<List<BlogComment>> getCommentByBlogId(@PathVariable("blogId") Long blogId){
        List<BlogComment> blogComments = blogCommentService.getAllCommentByBlogId(blogId);
        return ResponseEntity.ok(blogComments);
    }

    @DeleteMapping("/deleteBlogComment/{id}")
    public ResponseEntity<String> deleteBlogComment(@PathVariable("id")Long commentId){
        blogCommentService.deleteCommentBlog(commentId);
        return  ResponseEntity.ok("BlogComment deleted successfully!.");
    }

//    ----------------------------------------------Blog View-----------------------------------------------------------

    @GetMapping("/BlogView-create/{userId}/{blogId}")
    public ResponseEntity<String> createBlogView(@PathVariable("userId") Long userId ,@PathVariable("blogId") Long blogId ) throws Throwable {
        Blog blog = blogService.getBlogById(blogId);
        return ResponseEntity.ok(blogViewService.createBlogView(userId, blog));
    }

    @GetMapping("/BlogView-count/{blogId}")
    public ResponseEntity<Long> getBlogViewCount(@PathVariable("blogId")Long blogId){
        Long count =blogViewService.getBlogViewCountForBlog(blogId);
        return ResponseEntity.ok(count);
    }


    //------------------------------------------------Blog Rating --------------------------------------------------------

    @GetMapping("/BlogRating-create/{userId}/{blogId}/{rate}")
    public ResponseEntity<String> createBlogRating(@PathVariable("userId")Long userId,@PathVariable("blogId")Long blogId ,@PathVariable("rate")int rate) throws Throwable {
        Blog blog = blogService.getBlogById(blogId);
        return ResponseEntity.ok(blogRatingService.createBlogRating(userId,blog,rate));
    }

    @GetMapping("/getBlogRating/{blogId}")
    public  ResponseEntity<Float> getBlogRating(@PathVariable("blogId")Long blogId){
        float rate =blogRatingService.getBlogRating(blogId);
        return  ResponseEntity.ok(rate);
    }

    //    ------------------------------------------------------Attendance------------------------------------------------
    @GetMapping("/createAttendance/{userId}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<Attendance> createAttendance(@PathVariable("userId") Long userId){
        Attendance attendance = attendanceService.createAttendance(userId);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/addBreakTime/{id}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<String> addBreakTime(@PathVariable("id") Long id){
        String attendance = attendanceService.updateBreakTime(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/addContinueTime/{id}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<String> addContinueTime(@PathVariable("id") Long id){
        String attendance = attendanceService.addContinueTime(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/updateCheckout/{id}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<String> updateCheckOut(@PathVariable("id") Long id){
        String attendance = attendanceService.updateCheckout(id);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/getAllAttendanceByUserId/{userId}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<List<Attendance>> getAllAttendanceByUserId(@PathVariable("userId") Long userId){
        List<Attendance> attendance = attendanceService.getAllAttendanceByUserId(userId);
        return ResponseEntity.ok(attendance);
    }
//
    @GetMapping("/getAttendanceById/{id}")
    public  ResponseEntity<Attendance> getAttendanceById(@PathVariable("id") Long id){
        Attendance attendance = attendanceService.getAttendanceById(id);
        return ResponseEntity.ok(attendance);
    }

//    @PostMapping("/getAttendanceDateRange")
//    public ResponseEntity<List<Attendance>> getAttendanceDateRange(
//            @RequestBody SearchDateRangeDto searchDateRangeDto
//    ) {
//        System.out.println("hi");
//        List<Attendance> attendances = attendanceService.getAttendanceByDateRange(searchDateRangeDto);
//        return ResponseEntity.ok(attendances);
//    }

    @GetMapping("/getAttendanceByDate/{userId}/{date}")
//    @PreAuthorize("hasAuthority('Teamlead')")
    public ResponseEntity<List<Attendance>> getAttendanceByDate(@PathVariable("userId") Long userId,@PathVariable("date") String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate parsedDate = LocalDate.parse(date, formatter);
        List<Attendance> attendance = attendanceService.getAttendanceByDate(userId,parsedDate);
        return ResponseEntity.ok(attendance);
    }

//    ---------------------------------------------project Task--------------------------------
@PostMapping("/createProjectTask")
    public ResponseEntity<ProjectTask> createProjectTask(@RequestBody ProjectTaskCreationDto projectTaskCreationDto) {
        ProjectTask projectTask = projectTaskService.createProjectTask(projectTaskCreationDto);
        return ResponseEntity.ok(projectTask);
    }
    @GetMapping("/getEmployeeByProjectId/{id}")
    public  ResponseEntity<List<EmployeeDto>> getEmployeeByProjectId(@PathVariable("id") Long id){
        List<EmployeeDto> members = projectTaskService.getEmployeeByProjectId(id);
        return ResponseEntity.ok(members);
    }

}
