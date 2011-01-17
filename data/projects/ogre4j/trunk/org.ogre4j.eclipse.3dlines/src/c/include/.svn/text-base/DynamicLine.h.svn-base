#ifndef _DYNAMIC_LINE_H_
#define _DYNAMIC_LINE_H_

#include "DynamicRenderable.h"
#include <vector>

namespace Ogre4j
{

class DynamicLine : public DynamicRenderable
{
	typedef Ogre::Vector3 Vector3;
	typedef Ogre::Real Real;
	typedef Ogre::RenderOperation::OperationType OperationType;

protected:
	/// Implementation DynamicRenderable, creates a simple vertex-only decl
	virtual void createVertexDeclaration();
	/// Implementation DynamicRenderable, pushes point list out to hardware memory
	virtual void fillHardwareBuffers();

private:
	std::vector<Vector3> mPoints;
	bool mDirty;

public:
	/// Constructor - see setOperationType() for description of argument.
	DynamicLine();
	virtual ~DynamicLine();

	/// Add a point to the point list
	void addPoint(const Ogre::Vector3 &p);
	/// Add a point to the point list
	void addPoint(Real x, Real y, Real z);

	/// Change the location of an existing point in the point list
	void setPoint(unsigned short index, const Vector3 &value);

	/// Return the location of an existing point in the point list
	const Vector3& getPoint(unsigned short index) const;

	/// Return the total number of points in the point list
	unsigned short getNumPoints(void) const;

	/// Remove all points from the point list
	void clear();

	/// Call this to update the hardware buffer after making changes.  
	void update();

	OperationType getOperationType() const;
};

}

#endif // _DYNAMIC_LINE_H_