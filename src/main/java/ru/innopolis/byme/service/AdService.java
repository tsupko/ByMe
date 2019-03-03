package ru.innopolis.byme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.innopolis.byme.dao.api.AdDao;
import ru.innopolis.byme.dao.api.UserDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.User;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

@Service
public class AdService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AdDao adDao;

    public void createAd(Ad ad, String login) {
        Optional<User> optionalUser = userDao.selectByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            ad.setUserId(user.getId());
            ad.setConfirm(true);
            ad.setActual(true);
            ad.setPriceMin(new BigDecimal(0));
            adDao.create(ad);
        }
    }

    public void updateAd(int id, Ad ad) {
        Ad newAd = adDao.selectById(id);
        newAd.setTitle(ad.getTitle());
        newAd.setText(ad.getText());
        newAd.setCategoryId(ad.getCategoryId());
        newAd.setPrice(ad.getPrice());
        newAd.setPriceMin(new BigDecimal(0));
        adDao.update(newAd);
    }

    public Collection<Ad> selectByUser(User user){
        return adDao.selectByUserId(user.getId());
    }

    public Ad selectById(int id) {
        return adDao.selectById(id);
    }

    public void delete(Ad ad) {
        adDao.delete(ad);
    }

}
