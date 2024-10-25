import Image from 'next/image';
import searchIcon from 'bootstrap-icons/icons/search.svg';
import bellIcon from 'bootstrap-icons/icons/bell.svg';
import gearIcon from 'bootstrap-icons/icons/gear.svg';

const PageTitleUp = () => {
  return (
    <div className="flex justify-between items-center py-3">
      <h1>Index</h1>
      <div className="flex items-center">
        <input type="search" className="form-control me-2" placeholder="Search..." />
        <Image src={searchIcon} alt="Search" width={30} height={30} />
        <Image src={bellIcon} alt="Notifications" width={30} height={30} className="ms-2" />
        <Image src={gearIcon} alt="Settings" width={30} height={30} className="ms-2" />
      </div>
    </div>
  );
};

export default PageTitleUp;