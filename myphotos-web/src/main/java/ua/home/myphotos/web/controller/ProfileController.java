package ua.home.myphotos.web.controller;

import ua.home.myphotos.model.Pageable;
import ua.home.myphotos.model.SortMode;
import ua.home.myphotos.model.domain.Photo;
import ua.home.myphotos.model.domain.Profile;
import ua.home.myphotos.service.PhotoService;
import ua.home.myphotos.service.ProfileService;
import ua.home.myphotos.web.util.RoutingUtils;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static ua.home.myphotos.web.Constants.PHOTO_LIMIT;


/**
 * Created by vov on 09.08.2017.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class ProfileController extends HttpServlet {

    private final List<String> homeUrls;

    @EJB
    private ProfileService profileService;

    @EJB
    private PhotoService photoService;

    public ProfileController() {
        this.homeUrls = Collections.unmodifiableList(Arrays.asList("/"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String url = req.getRequestURI();
      if (isHomeUrl(url)){
          handleHomeRequest(req, resp);
      } else {
          handleProfileRequest(req, resp);
      }
    }
    private boolean isHomeUrl(String url) {
        return homeUrls.contains(url);
    }
    private void handleProfileRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uid = req.getRequestURI().substring(1);
        Profile profile = profileService.findByUid(uid);
        req.setAttribute("profile", profile);
        req.setAttribute("profilePhotos", Boolean.TRUE);
        List<Photo> photos = photoService.findProfilePhotos(profile.getId(), new Pageable(1, PHOTO_LIMIT));
        req.setAttribute("photos", photos);

        RoutingUtils.forwardToPage("profile", req, resp);

    }

    private SortMode getSortMode(HttpServletRequest req) {
        Optional<String> sortMode = Optional.ofNullable(req.getParameter("sort"));
        return sortMode.isPresent() ? SortMode.of(sortMode.get()) : SortMode.POPULAR_PHOTO;
    }

    private void handleHomeRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SortMode sortMode = getSortMode(req);
        List<Photo> photos = photoService.findPopularPhotos(sortMode, new Pageable(1, PHOTO_LIMIT));
        req.setAttribute("photos", photos);
        long totalCount = photoService.countAllPhotos();
        req.setAttribute("totalCount", totalCount);
        req.setAttribute("sortMode", sortMode.name().toLowerCase());
        RoutingUtils.forwardToPage("home", req, resp);
    }


}
