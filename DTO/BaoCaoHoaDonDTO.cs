using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DTO
{
    class BaoCaoHoaDonDTO
    {
        private string _MaBaoCaoHoaDon;
        private string _MaHoaDon;
        private int _Thang;

        public string MaBaoCaoHoaDon
        {
            get
            {
                return _MaBaoCaoHoaDon;
            }

            set
            {
                _MaBaoCaoHoaDon = value;
            }
        }

        public string MaHoaDon
        {
            get
            {
                return _MaHoaDon;
            }

            set
            {
                _MaHoaDon = value;
            }
        }

        public int Thang
        {
            get
            {
                return _Thang;
            }

            set
            {
                _Thang = value;
            }
        }

        private void New()
        {
            _MaBaoCaoHoaDon = null;
            _MaHoaDon = null;
           
        }
        
    }
}
